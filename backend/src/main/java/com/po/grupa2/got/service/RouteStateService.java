package com.po.grupa2.got.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.po.grupa2.got.dto.RouteStateChanged;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;
import com.po.grupa2.got.repository.RouteStateRepository;

@Service
public class RouteStateService {
	
	private static final String START_CHANGED = "Start date change";
	private static final String END_CHANGED = "End date change";
	private static final String REMOVED = "Removed";
	private static final String SPLITED = "Splited into two states";
	
	private static final long DAY_IN_MILIS = 24*60*60*1000;
	
	@Autowired
	private RouteStateRepository routeStateRepository;
	
	public RouteState getCurrentState(Route route) {	
		Date now = new Date(System.currentTimeMillis());
		return getStateForDate(route, now);
	}
	
	public RouteState getStateForDate(Route route, Date date) {
		for (RouteState state : route.getRouteStates()) {
			if(state.getStart().before(new Date(date.getTime() + 1)) && (state.getEnd() == null || state.getEnd().after(new Date(date.getTime() - 1))))
					return state;
		}
		
		return null;
	}
	
	public List<Route> filterOpenFromLocalization(List<Route> routes, long localiztion_id, Date date) {
		for(int i = 0; i < routes.size(); i++) {
			Route route = routes.get(i);
			RouteState state = getStateForDate(route, date);
			
			int points = localiztion_id != route.getStart().getId()? state.getPointsBack(): state.getPointsThere();
			
			if(!state.isOpen() || points < 0) {
				routes.remove(i);
			}
		}
		return routes;
	}
	
	public List<RouteStateChanged> getRequriedChanges(Route route, Date start, Date end, long state_id){
		List<RouteStateChanged> changes = new LinkedList<>();
		
		for (RouteState state : route.getRouteStates()) {
			if(state.getId() == state_id)
				continue;
			
			if(state.getStart().after(previuysDay(start)) && state.getStart().before(nextDay(end))) {
				if(state.getEnd() == null || state.getEnd().after(end))
					changes.add(new RouteStateChanged(state, START_CHANGED));
				else
					changes.add(new RouteStateChanged(state, REMOVED));
			}	
			else if(state.getStart().before(start)){
				if(state.getEnd() == null || state.getEnd().after(end))
					changes.add(new RouteStateChanged(state, SPLITED));
				else if(state.getEnd().after(previuysDay(start)))
					changes.add(new RouteStateChanged(state, END_CHANGED));
			}
		}
		
		return changes;
	}
	
	public List<RouteState> saveChanges(RouteState stateToSave){
		List<RouteState> saved = new LinkedList<>();
		
		for (RouteState state : stateToSave.getRoute().getRouteStates()) {
			if(state.getId() == stateToSave.getId())
				continue;
			
			if(state.getStart().after(previuysDay(stateToSave.getStart())) && state.getStart().before(nextDay(stateToSave.getEnd()))) {
				if(state.getEnd() == null || state.getEnd().after(stateToSave.getEnd())) {
					state.setStart(nextDay(stateToSave.getEnd()));
					saved.add(routeStateRepository.save(state));
				}
				else
					routeStateRepository.delete(state);
			}	
			else if(state.getStart().before(stateToSave.getStart())){
				if(state.getEnd() == null || state.getEnd().after(stateToSave.getEnd())) {
					RouteState newState = new RouteState(state.getStart(), 
														previuysDay(stateToSave.getStart()), 
														state.isOpen(), 
														state.getPointsThere(), 
														state.getPointsBack(), 
														state.getRoute());
					state.setStart(nextDay(stateToSave.getEnd()));
					saved.add(routeStateRepository.save(newState));
					saved.add(routeStateRepository.save(state));
				}
				else if(state.getEnd().after(previuysDay(stateToSave.getStart()))) {
					state.setEnd(previuysDay(stateToSave.getStart()));
					saved.add(routeStateRepository.save(state));
				}
			}
		}
		
		saved.add(routeStateRepository.save(stateToSave));
		
		return saved;
	}
	
	public boolean validate(RouteState state) {
		if(	state.getStart() == null || 
			state.getEnd() == null ||
			state.getStart().after(state.getEnd()) ||
			(state.getPointsThere() <= 0 && state.getPointsBack() <= 0) ||
			state.getRoute() == null)
			return false;
		
		return true;
	}
	
	private Date nextDay(Date date) {
		return new Date(date.getTime() + DAY_IN_MILIS);
	}
	
	private Date previuysDay(Date date) {
		return new Date(date.getTime() - DAY_IN_MILIS);
	}
}

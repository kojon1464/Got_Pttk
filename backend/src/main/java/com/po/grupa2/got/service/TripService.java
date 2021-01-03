package com.po.grupa2.got.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.model.Localization;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;
import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.TripSegment;
import com.po.grupa2.got.model.TripSegmentKey;

@Service
public class TripService {

	@Autowired
	private RouteStateService routeStateService;
	
	private boolean validateTrip(Trip trip) {
		
		if(trip.getDate() == null ||
			trip.getStart() == null ||
			trip.isConfirmed() == true||
			trip.getName() == null || trip.getName().equals("") ||
			trip.getUser() == null)
			return false;
		
		return true;
	}
	
	public ErrorDTO validateTripAndSegments(Trip trip, List<TripSegment> segments) {
		if(!validateTrip(trip)) {
			return new ErrorDTO("Trip could not be validated");
		}
		
		Localization lastLocalization = trip.getStart();
		
		for (TripSegment tripSegment : segments) {
			Route route = tripSegment.getRoute();
			RouteState state = routeStateService.getStateForDate(route, trip.getDate());
			
			if(route.getStart().getId() != lastLocalization.getId()) {
				if(route.getEnd().getId() != lastLocalization.getId())
					return new ErrorDTO("Routes does not connect");
				
				if(!state.isOpen())
					return new ErrorDTO("Route from " + route.getEnd().getName() + " to " + route.getStart().getName() + "is closed");
				
				if(state.getPointsBack() < 0) 
					return new ErrorDTO("Route from " + route.getEnd().getName() + " to " + route.getStart().getName() + "is closed in given direction");
				
				lastLocalization = route.getStart();
			} else {
				if(route.getStart().getId() != lastLocalization.getId())
					return new ErrorDTO("Routes does not connect");
				
				if(!state.isOpen())
					return new ErrorDTO("Route from " + route.getStart().getName() + " to " + route.getEnd().getName() + "is closed");
				
				if(state.getPointsThere() < 0) 
					return new ErrorDTO("Route from " + route.getStart().getName() + " to " + route.getEnd().getName() + "is closed in given direction");
				
				lastLocalization = route.getEnd();
			}
		}
		
		return null;
	}
	
	public int calculatePointsForTrip(Trip trip, List<TripSegment> segments) {
		int sum = 0;
		HashSet<RoutePass> hashset = new HashSet<>();
		Localization lastLocalization = trip.getStart();
		
		for (TripSegment tripSegment : segments) {
			Route route = tripSegment.getRoute();
			RouteState state = routeStateService.getStateForDate(route, trip.getDate());
			
			if(route.getStart().getId() != lastLocalization.getId()) {
				RoutePass pass = new RoutePass(tripSegment.getId(), Direction.BACK);
				
				if(!hashset.contains(pass)) {
					sum += state.getPointsBack();
					hashset.add(pass);
				}
			} else {
				RoutePass pass = new RoutePass(tripSegment.getId(), Direction.THERE);
				
				if(!hashset.contains(pass)) {
					sum += state.getPointsThere();
					hashset.add(pass);
				}
			}
		}
		
		return sum;
	}
	
	public int calculatePointsForTrip(Trip trip) {
		return calculatePointsForTrip(trip, trip.getSegments());
	}
	
	private static enum Direction {
		BACK, THERE
	}
	
	private static class RoutePass {
		
		private TripSegmentKey key;
		private Direction direction;
		
		public RoutePass(TripSegmentKey key, Direction direction) {
			this.key = key;
			this.direction = direction;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((direction == null) ? 0 : direction.hashCode());
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RoutePass other = (RoutePass) obj;
			if (direction != other.direction)
				return false;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			return true;
		}
	}
}

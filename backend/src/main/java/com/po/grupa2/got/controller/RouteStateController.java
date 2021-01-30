package com.po.grupa2.got.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.dto.RouteStateChanged;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;
import com.po.grupa2.got.repository.RouteRepository;
import com.po.grupa2.got.repository.RouteStateRepository;
import com.po.grupa2.got.service.RouteStateService;

@RestController
public class RouteStateController {
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private RouteStateRepository routeStateRepository;
	
	@Autowired
	private RouteStateService routeStateService;
	
	@GetMapping("/states")
	private ResponseEntity<List<RouteState>> routeStates(long route_id) throws Exception {
		return ResponseEntity.ok(routeStateRepository.findByRouteId(route_id));
	}
	
	@PostMapping("/state-change")
	private ResponseEntity<?> requredChanges(@RequestBody RouteState state) throws Exception {
		
		if(!routeStateService.validate(state))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("State cannot be validated"));
		
		Optional<Route> route = routeRepository.findById(state.getRoute().getId());
		
		if(route.get() == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Cannot find route with id: " + state.getRoute().getId()));
		
		List<RouteStateChanged> changes = routeStateService.getRequriedChanges(route.get(), state.getStart(), state.getEnd(), state.getId());
		changes.add(0, new RouteStateChanged(state, "Wprowadzone zmiany"));
		
		return ResponseEntity.ok(changes);
	}
	
	@PostMapping("/confirm-change")
	private ResponseEntity<?> confirmChanges(@RequestBody RouteState state) throws Exception {
		
		Optional<Route> route = routeRepository.findById(state.getRoute().getId());
		if(route.get() == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Cannot find route with id: " + state.getRoute().getId()));
		state.setRoute(route.get());
		
		if(!routeStateService.validate(state))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("State cannot be validated"));
		
		return ResponseEntity.ok(routeStateService.saveChanges(state));
	}
}

package com.po.grupa2.got.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.dto.RouteDTO;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.repository.RouteRepository;
import com.po.grupa2.got.service.RouteStateService;

@RestController
public class RouteController {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private RouteStateService routeStateService;
	
	@GetMapping("/routes")
	private ResponseEntity<List<RouteDTO>> routes() throws Exception {
		List<Route> routes = routeRepository.findAll();
		return ResponseEntity.ok(routes
								.parallelStream()
								.map(r -> new RouteDTO(r, routeStateService.getCurrentState(r)))
								.collect(Collectors.toList()));
	}
	
	@GetMapping("/matching-routes")
	private ResponseEntity<List<Route>> routesMatching(long id) throws Exception {
		return ResponseEntity.ok(routeRepository.findByStartIdOrEndId(id, id));
	}
}

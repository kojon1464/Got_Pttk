package com.po.grupa2.got.controller;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.dto.TripDTO;
import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.TripSegment;
import com.po.grupa2.got.model.TripSegmentKey;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.RouteRepository;
import com.po.grupa2.got.repository.TripRepository;
import com.po.grupa2.got.repository.TripSegmentRepository;
import com.po.grupa2.got.repository.UserRepository;
import com.po.grupa2.got.service.TripService;

@RestController
public class TripController {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private TripSegmentRepository tripSegmentRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TripService tripService;

	@GetMapping("/trips")
	public ResponseEntity<List<Trip>> routes(Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());
		return ResponseEntity.ok(tripRepository.findByUser(user));
	}

	@Transactional
	@PostMapping("/save-trip")
	public ResponseEntity<?> routes(@RequestBody TripDTO tripDTO, Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());

		Trip trip = tripDTO.getTrip();
		trip.setUser(user);

		trip = tripRepository.save(trip);
		List<TripSegment> segments = prepareSegmentsList(tripDTO);

		ErrorDTO validation = tripService.validateTripAndSegments(trip, segments);

		if (validation != null) {
			tripRepository.delete(trip);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
		}

		tripSegmentRepository.deleteByTrip(trip);

		tripSegmentRepository.saveAll(segments);

		return ResponseEntity.ok("sucess");
	}

	@PostMapping("/points")
	public ResponseEntity<Integer> points(@RequestBody TripDTO tripDTO) throws Exception {
		int points = 0;

		try {
			points = tripService.calculatePointsForTrip(tripDTO.getTrip(), prepareSegmentsList(tripDTO));
		} catch (Exception e) {

		}

		return ResponseEntity.ok(points);
	}
	
	private List<TripSegment> prepareSegmentsList(TripDTO tripDTO) {
		List<TripSegment> segments = tripDTO.getSegments();

		for (int i = 0; i < segments.size(); i++) {
			TripSegment segment = segments.get(i);

			segment.setTrip(tripDTO.getTrip());
			segment.setRoute(routeRepository.findById(segment.getRoute().getId()).get());
			segment.setId(new TripSegmentKey(tripDTO.getTrip().getId(), segment.getRoute().getId()));
			segment.setOrderNumber(i);
		}
		
		return segments;
	}
}

package com.po.grupa2.got.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.BadGateway;

import com.po.grupa2.got.dto.BadgeDetailsDTO;
import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.dto.RankAddressDTO;
import com.po.grupa2.got.model.Address;
import com.po.grupa2.got.model.BadgeAchievement;
import com.po.grupa2.got.model.BadgeApplication;
import com.po.grupa2.got.model.BadgeArchievmentKey;
import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.AddressRepository;
import com.po.grupa2.got.repository.BadgeAchievementRepository;
import com.po.grupa2.got.repository.BadgeApplicationRepository;
import com.po.grupa2.got.repository.BadgeRankRepository;
import com.po.grupa2.got.repository.UserRepository;
import com.po.grupa2.got.service.AddressService;
import com.po.grupa2.got.service.BadgeService;
import com.po.grupa2.got.service.TripService;

@RestController
public class BadgeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BadgeRankRepository badgeRankRepository;
	
	@Autowired
	private BadgeApplicationRepository badgeApplicationRepository;
	
	@Autowired
	private BadgeAchievementRepository badgeAchievmentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BadgeService badgeService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private AddressService addressService;

	@GetMapping("/badges-to-collect")
	public ResponseEntity<List<BadgeRank>> badgesToCollect(Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());
		return ResponseEntity.ok(badgeService.getBadgesToCollect(user));
	}
	
	@GetMapping("/badge")
	public ResponseEntity<?> badge(long id, Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());
		BadgeRank rank = badgeRankRepository.findById(id).get();
		BadgeApplication application = badgeApplicationRepository.findTopByBadgeRankAndUserOrderByCreationDateDesc(rank, user);
		int points = 0;
		int pointsExcessive = 0;
		
		if(application == null) {
			List<BadgeAchievement> archivments = badgeAchievmentRepository.findByUserOrderByDate(user);
			
			if(archivments.size() > 0) {
				Date date = archivments.get(archivments.size() - 1).getDate();
				points = tripService.calculatePointsForTripsAfter(date, user);
				
				if(archivments.size() > 1 && rank.isWith_excess()) {
					Date start = archivments.get(archivments.size() - 2).getDate();
					int pointsForLastRank = archivments.get(archivments.size() - 2).getBadgeRank().getPoints();
					pointsExcessive = tripService.calculatePointsForTripsBetween(start, date, user) - pointsForLastRank;
				}
			} else {
				points = tripService.calculatePointsForAllTrips(user);
			}
		}
		
		return ResponseEntity.ok(new BadgeDetailsDTO(rank, application, points, pointsExcessive));
	}
	
	@GetMapping("/create-application")
	public ResponseEntity<?> createApplication(long id, Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());
		BadgeRank rank = badgeRankRepository.findById(id).get();
		BadgeApplication application = badgeApplicationRepository.findTopByBadgeRankAndUserOrderByCreationDateDesc(rank, user);
		BadgeAchievement thisAchivement = badgeAchievmentRepository.findByUserAndBadgeRank(user, rank);
		int points = 0;
		int pointsExcessive = 0;
		
		if(thisAchivement != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		if(application == null) {
			List<BadgeAchievement> archivments = badgeAchievmentRepository.findByUserOrderByDate(user);
			
			if(archivments.size() > 0) {
				Date date = archivments.get(archivments.size() - 1).getDate();
				points = tripService.calculatePointsForTripsAfter(date, user);
				
				if(archivments.size() > 1 && rank.isWith_excess()) {
					Date start = archivments.get(archivments.size() - 2).getDate();
					int pointsForLastRank = archivments.get(archivments.size() - 2).getBadgeRank().getPoints();
					pointsExcessive = tripService.calculatePointsForTripsBetween(start, date, user) - pointsForLastRank;
				}
			} else {
				points = tripService.calculatePointsForAllTrips(user);
			}
			
			if(rank.getPoints() <= points + Math.max(0, pointsExcessive)) {
				badgeApplicationRepository.save(new BadgeApplication(Calendar.getInstance().getTime(), false, false, rank, user));
				return ResponseEntity.ok("sucess");
			}
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@PostMapping("/address")
	public ResponseEntity<?> badge(@RequestBody RankAddressDTO dto, Principal principal) throws Exception {
		User user = userRepository.findByEmailIgnoreCase(principal.getName());
		BadgeRank rank = badgeRankRepository.findById(dto.getRank().getId()).get();
		BadgeApplication application = badgeApplicationRepository.findTopByBadgeRankAndUserOrderByCreationDateDesc(rank, user);
		BadgeAchievement thisAchivement = badgeAchievmentRepository.findByUserAndBadgeRank(user, rank);
		
		if(thisAchivement != null || application == null || !application.isChecked() || !application.isPositive())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		Address address = dto.getAddress();
		
		ErrorDTO error = addressService.validateAddress(address);
		
		if(error != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		addressRepository.save(address);
		
		application.setAddress(address);
		badgeApplicationRepository.save(application);
		
		badgeAchievmentRepository.save(new BadgeAchievement(rank, user, Calendar.getInstance().getTime()));
		
		return ResponseEntity.ok("sucess");
	}
}

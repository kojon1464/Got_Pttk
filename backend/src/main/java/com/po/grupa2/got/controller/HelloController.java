package com.po.grupa2.got.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.model.TripSegment;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.TripSegmentRepository;
import com.po.grupa2.got.repository.UserRepository;

@RestController
public class HelloController {
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//
	//
	// !!!!!!!!!!!! THIS is a test controller !!!!!!!!!!!!!!!!!!!!
	//
	//
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TripSegmentRepository tripSegmentsRepository;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping("/users")
	public List<User> users() {
		return userRepository.findAll();
	}
	
	@RequestMapping("/segments")
	public List<TripSegment> segments() {
		return tripSegmentsRepository.findAll();
	}
}

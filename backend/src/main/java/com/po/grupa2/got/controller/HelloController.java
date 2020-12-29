package com.po.grupa2.got.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.UserRepository;

@RestController
public class HelloController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping("/users")
	public List<User> users() {
		return userRepository.findAll();
	}
}

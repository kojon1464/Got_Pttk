package com.po.grupa2.got.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.po.grupa2.got.dto.UserDTO;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailIgnoreCase(username);
		if(user == null || !user.isEnabled()) {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}
	
	public User save(UserDTO user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
	
	public User confrimUser(User user) {
        user.setEnabled(true);
        return userRepository.save(user);
	}
}
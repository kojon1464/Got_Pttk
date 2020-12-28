package com.po.grupa2.got.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.po.grupa2.got.config.JwtTokenUtil;
import com.po.grupa2.got.dto.ErrorDTO;
import com.po.grupa2.got.dto.JwtRequest;
import com.po.grupa2.got.dto.JwtResponse;
import com.po.grupa2.got.dto.UserDTO;
import com.po.grupa2.got.model.ConfirmationToken;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.service.ConfirmationService;
import com.po.grupa2.got.service.EmailSenderService;
import com.po.grupa2.got.service.MyUserDetailsService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private ConfirmationService confirmationService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO user) throws Exception {
		if(user.getEmail() == null || user.getEmail().equals("")) {
			return ResponseEntity.ok(new ErrorDTO("Email cannot be empty"));
		}
		else if(user.getPassword() == null || user.getPassword().equals("")) {
			return ResponseEntity.ok(new ErrorDTO("Password cannot be empty"));
		}
		
		try {
			User createdUser = userDetailsService.save(user);
			confirmationService.createConfirmationToken(createdUser);
			return ResponseEntity.ok(createdUser);
			
		} 
		catch (DataIntegrityViolationException e) {
			return ResponseEntity.ok(new ErrorDTO("Dublicated emails"));
		}

	}
	
	@GetMapping("/confirm-account")
	public ResponseEntity<?> confirmUser(@RequestParam("token")String confirmationToken) throws Exception {
		ConfirmationToken token = confirmationService.getToken(confirmationToken);
		
		if(token != null) {
			return ResponseEntity.ok(userDetailsService.confrimUser(token.getUser()));
		}
		else {
			return ResponseEntity.ok(new ErrorDTO("Invalid Token"));
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
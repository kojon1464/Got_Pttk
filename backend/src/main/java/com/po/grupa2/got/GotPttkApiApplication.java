package com.po.grupa2.got;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.UserRepository;

@SpringBootApplication
public class GotPttkApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GotPttkApiApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.save(new User("test@wp.pl", passwordEncoder.encode("password"), true));
	}

}

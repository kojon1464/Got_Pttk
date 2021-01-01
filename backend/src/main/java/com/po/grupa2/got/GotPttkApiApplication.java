package com.po.grupa2.got;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.po.grupa2.got.model.Localization;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.LocalizationRepository;
import com.po.grupa2.got.repository.RouteRepository;
import com.po.grupa2.got.repository.RouteStateRepository;
import com.po.grupa2.got.repository.UserRepository;

@SpringBootApplication
public class GotPttkApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GotPttkApiApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private LocalizationRepository localizationRepository;
	
	@Autowired
	private RouteStateRepository routeStateRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.save(new User("test@wp.pl", passwordEncoder.encode("password"), true));
		
		Localization lokalizationBoj = new Localization("Bojanowo");
		Localization lokalizationLesz = new Localization("Leszno");
		
		localizationRepository.save(lokalizationBoj);
		localizationRepository.save(lokalizationLesz);
		
		Route route = new Route("", lokalizationBoj, lokalizationLesz);
		
		routeRepository.save(route);
		
		routeStateRepository.save(new RouteState(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime(), new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime(), true, 7, 5, route));
		routeStateRepository.save(new RouteState(new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime(), new GregorianCalendar(2021, Calendar.FEBRUARY, 11).getTime(), false, 4, 5, route));
	}

}

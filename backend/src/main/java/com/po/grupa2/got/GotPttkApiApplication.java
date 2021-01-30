package com.po.grupa2.got;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.po.grupa2.got.model.Badge;
import com.po.grupa2.got.model.Badge.Kind;
import com.po.grupa2.got.model.BadgeAchievement;
import com.po.grupa2.got.model.BadgeApplication;
import com.po.grupa2.got.model.BadgeArchievmentKey;
import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.BadgeRank.Rank;
import com.po.grupa2.got.model.Localization;
import com.po.grupa2.got.model.Route;
import com.po.grupa2.got.model.RouteState;
import com.po.grupa2.got.model.Trip;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.BadgeAchievementRepository;
import com.po.grupa2.got.repository.BadgeApplicationRepository;
import com.po.grupa2.got.repository.BadgeRankRepository;
import com.po.grupa2.got.repository.BadgeRepository;
import com.po.grupa2.got.repository.LocalizationRepository;
import com.po.grupa2.got.repository.RouteRepository;
import com.po.grupa2.got.repository.RouteStateRepository;
import com.po.grupa2.got.repository.TripRepository;
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
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private BadgeRankRepository badgeRankRepository;
	
	@Autowired
	private BadgeRepository badgeRepository;
	
	@Autowired
	private BadgeAchievementRepository badgeAchievementRepository;
	
	@Autowired
	private BadgeApplicationRepository badgeApplicationRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("test@wp.pl", passwordEncoder.encode("password"), true);
		User user1 = new User("halo@wp.pl", passwordEncoder.encode("password"), false);
		
		userRepository.save(user);
		userRepository.save(user1);
		
		Localization lokalizationHala = new Localization("Schronisko PTTK Hala Szrenicka");
		Localization lokalizationSzrenica = new Localization("Szrenica");
		Localization lokalizationPrzelecz = new Localization("Mokra przełęcz");
		
		localizationRepository.save(lokalizationHala);
		localizationRepository.save(lokalizationSzrenica);
		localizationRepository.save(lokalizationPrzelecz);
		
		Route route = new Route("", lokalizationHala, lokalizationSzrenica);
		Route route1 = new Route("", lokalizationSzrenica, lokalizationPrzelecz);
		
		routeRepository.save(route);
		routeRepository.save(route1);
		
		routeStateRepository.save(new RouteState(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime(), new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime(), true, 3, 1, route));
		routeStateRepository.save(new RouteState(new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime(), new GregorianCalendar(2021, Calendar.FEBRUARY, 15).getTime(), false, 3, 1, route));
		routeStateRepository.save(new RouteState(new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime(), new GregorianCalendar(2025, Calendar.FEBRUARY, 11).getTime(), true, 1, 2, route1));
		
		Trip trip = new Trip(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime(), "Pierwsza", "", false, lokalizationHala, user);
		
		tripRepository.save(trip);
		
		
		Badge badge1 = new Badge(Kind.DUZA, "Great");
		Badge badge2 = new Badge(Kind.MALA, "Small");
		
		badgeRepository.save(badge1);
		badgeRepository.save(badge2);
		
		BadgeRank rank1 = new BadgeRank(Rank.SREBRNA, "Dużą GOT PTTK w stopniu srebrnym zdobywa się przez odbycie dwóch regulaminowych wycieczek wielodniowych w dwóch wybranych terenach górskich I–VI.\n" + 
				"Dodatkowo należy zdobyć 100 punktów w Górach Świętokrzyskich", 100, false, badge1);
		BadgeRank rank2 = new BadgeRank(Rank.ZLOTA, "Gold", 0, false, badge1);
		BadgeRank rank3 = new BadgeRank(Rank.SREBRNA, "Wymagana odznaka w stopniu brązowym", 360, true, badge2);
		BadgeRank rank4 = new BadgeRank(Rank.BRAZOWA, "Wymagana odznaka w stopniu popularnym", 120, true, badge2);
		
		badgeRankRepository.save(rank4);
		badgeRankRepository.save(rank3);
		
		badgeRankRepository.save(rank2);
		badgeRankRepository.save(rank1);
		
		
		badgeAchievementRepository.save(new BadgeAchievement(rank2, user1, new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime()));
		badgeAchievementRepository.save(new BadgeAchievement(rank2, user, new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime()));
		
		BadgeApplication application = new BadgeApplication(new GregorianCalendar(2020, Calendar.DECEMBER, 13).getTime(), true, true, rank1, user);
		application.setCheckDate(new GregorianCalendar(2021, Calendar.JANUARY, 5).getTime());
		
		badgeApplicationRepository.save(new BadgeApplication(new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime(), true, true, rank1, user));
		badgeApplicationRepository.save(application);
		badgeApplicationRepository.save(new BadgeApplication(new GregorianCalendar(2022, Calendar.DECEMBER, 12).getTime(), true, true, rank1, user1));
	}

}

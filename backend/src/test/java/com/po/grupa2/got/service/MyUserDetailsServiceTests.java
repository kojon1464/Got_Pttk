package com.po.grupa2.got.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.po.grupa2.got.dto.UserDTO;
import com.po.grupa2.got.model.BadgeAchievement;
import com.po.grupa2.got.model.BadgeRank;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.BadgeRankRepository;
import com.po.grupa2.got.repository.UserRepository;


@RunWith(SpringRunner.class)
public class MyUserDetailsServiceTests {
	
    @TestConfiguration
    static class BadgeServiceTestContextConfiguration {
 
        @Bean
        public MyUserDetailsService userDetailsService() {
            return new MyUserDetailsService();
        }
    }
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
    @Test
    public void loadUserByUsername_normal_scenario() {   
    	String email = "test@wp.pl";
    	String password =  "password";
    	Mockito.when(userRepository.findByEmailIgnoreCase(email)).thenReturn(new User(email, password, true));


        UserDetails result = userDetailsService.loadUserByUsername(email);
        
        Assert.assertEquals(email, result.getUsername());
        Assert.assertEquals(password, result.getPassword());
        Assert.assertTrue(result.getAuthorities().isEmpty());
    }
    
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_not_found() {   
    	String email = "test@wp.pl";
    	Mockito.when(userRepository.findByEmailIgnoreCase(email)).thenReturn(null);


        UserDetails result = userDetailsService.loadUserByUsername(email);
    }
    
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_disabled_user() {   
    	String email = "test@wp.pl";
    	String password =  "password";
    	Mockito.when(userRepository.findByEmailIgnoreCase(email)).thenReturn(new User(email, password, false));


        UserDetails result = userDetailsService.loadUserByUsername(email);
    }
   
    @Test
    public void confrimUser_disabled() {   
    	String email = "test@wp.pl";
    	String password =  "password";
    	User testUser = new User(email, password, false);
    	Mockito.when(userRepository.save(testUser)).thenReturn(new User(email, password, true));

        User result = userDetailsService.confrimUser(testUser);
        
        Assert.assertTrue(result.isEnabled());
    }
    
    @Test
    public void confrimUser_enabled() {   
    	String email = "test@wp.pl";
    	String password =  "password";
    	User testUser = new User(email, password, true);
    	Mockito.when(userRepository.save(testUser)).thenReturn(new User(email, password, true));


        User result = userDetailsService.confrimUser(testUser);
        
        Assert.assertTrue(result.isEnabled());
    }
    
    @Test
    public void save_pass() {   
    	String email = "test@wp.pl";
    	String password =  "password";
    	User testUser = new User(email, password, false);
    	Mockito.when(userRepository.save(testUser)).thenReturn(testUser);
    	
    	UserDTO dto = new UserDTO();
    	dto.setEmail(email);
    	dto.setPassword(password);
        User result = userDetailsService.save(dto);
        
        Assert.assertEquals(email, result.getEmail());
        Assert.assertEquals(password, result.getPassword());
        Assert.assertFalse(result.isEnabled());
    }
}

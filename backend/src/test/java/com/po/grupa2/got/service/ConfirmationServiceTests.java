package com.po.grupa2.got.service;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.BadgeRankRepository;
import com.po.grupa2.got.repository.ConfirmationTokenRepository;



@RunWith(SpringRunner.class)
public class ConfirmationServiceTests {
	
    @TestConfiguration
    static class ConfirmationServiceTestContextConfiguration {
 
        @Bean
        public ConfirmationService confirmationService() {
            return new ConfirmationService();
        }
    }
    
    @Captor
    private ArgumentCaptor<SimpleMailMessage> emailCaptor;
    
    @MockBean
    private ConfirmationTokenRepository confirmationTokenRepository;
    
	@MockBean
	private EmailSenderService emailSenderService;
	
	@Autowired
	private ConfirmationService confirmationService;
	
	@Mock
	User user;
	
    @Test
    public void createConfirmationToken_pass() {     
    	String email = "test@wp.pl";
    	Mockito.when(user.getEmail()).thenReturn(email);
    	
    	confirmationService.createConfirmationToken(user);
    	
    	verify(emailSenderService).sendEmail(emailCaptor.capture());
    	assertEquals(email, emailCaptor.getValue().getTo()[0]);
    }
    

}

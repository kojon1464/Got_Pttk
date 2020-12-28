package com.po.grupa2.got.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.po.grupa2.got.model.ConfirmationToken;
import com.po.grupa2.got.model.User;
import com.po.grupa2.got.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationService {
	
	@Value("${spring.mail.username}")
	private String from;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
    @Autowired
    private EmailSenderService emailSenderService;
    
    public ConfirmationToken getToken(String confirmationToken) {
    	return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }
    
    public void createConfirmationToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        sendConfirmationToken(user, confirmationToken);
    }
    
    @Async
    private void sendConfirmationToken(User user, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(this.from);
        mailMessage.setText("To confirm your account, please click here : "
        +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
    }
}

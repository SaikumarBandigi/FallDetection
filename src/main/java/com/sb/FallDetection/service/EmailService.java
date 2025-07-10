package com.sb.FallDetection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendFallAlert(String[] recipients, String deviceId, double acceleration) {
        // Send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject("Fall Detected Alert!");
        message.setText("Fall detected for device ID: " + deviceId +
                "\nAcceleration: " + acceleration +
                "\nTime: " + java.time.LocalDateTime.now());
        mailSender.send(message);


    }


// Twilio Recovery Code
    // K2PVB92PRTZ2BTUAWEBNZEQ5
}

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

    private static final String FAST2SMS_API_KEY = "Yel8napQJf6Ezh4PgZc7irdU0Iw9VFSos1BtqjMXAmvHK2xbyGmYbzSRI6QCH3vfkpnirl9sVUojO1ya"; // Replace

    public void sendFallAlert(String[] recipients, String deviceId, double acceleration) {
        // Send Email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject("Fall Detected Alert!");
        message.setText("Fall detected for device ID: " + deviceId +
                "\nAcceleration: " + acceleration +
                "\nTime: " + java.time.LocalDateTime.now());
        mailSender.send(message);

        // Send SMS
        sendSMS("Fall detected! Device: " + deviceId + ", Acceleration: " + acceleration);
    }

    private void sendSMS(String text) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String messageEncoded = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            String url = "https://www.fast2sms.com/dev/bulkV2?" +
                    "authorization=" + FAST2SMS_API_KEY +
                    "&sender_id=TXTIND" +
                    "&message=" + messageEncoded +
                    "&language=english" +
                    "&route=q" +
                    "&numbers=6305032504"; // Replace with target mobile number(s)

            restTemplate.getForObject(new URI(url), String.class);
        } catch (Exception e) {
            System.out.println("SMS sending failed: " + e.getMessage());
        }
    }

}

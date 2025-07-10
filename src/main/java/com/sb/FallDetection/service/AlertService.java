package com.sb.FallDetection.service;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Autowired
    private JavaMailSender mailSender;

    // Twilio credentials
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;


    // Twilio WhatsApp sandbox number
    private static final String TWILIO_WHATSAPP_NUMBER = "whatsapp:+14155238886";

    public AlertService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendFallAlert(String[] emailRecipients, String whatsappRecipientNumber, String deviceId, double acceleration) {
        sendEmailAlert(emailRecipients, deviceId, acceleration);
        sendWhatsAppAlert(whatsappRecipientNumber, deviceId, acceleration);
    }

    private void sendEmailAlert(String[] recipients, String deviceId, double acceleration) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject("Fall Detected Alert!");
        message.setText("Fall detected for device ID: " + deviceId +
                "\nAcceleration: " + acceleration +
                "\nTime: " + java.time.LocalDateTime.now());
        mailSender.send(message);
    }

    private void sendWhatsAppAlert(String toNumber, String deviceId, double acceleration) {
        try {
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + toNumber),
                            new PhoneNumber(TWILIO_WHATSAPP_NUMBER),
                            "Fall detected!\nDevice: " + deviceId + "\nAcceleration: " + acceleration + "\nPlease check immediately.")
                    .create();

            System.out.println("WhatsApp Message SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Failed to send WhatsApp message: " + e.getMessage());
        }
    }

}

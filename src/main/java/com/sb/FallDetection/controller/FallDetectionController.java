package com.sb.FallDetection.controller;



import com.sb.FallDetection.model.FallEvent;
import com.sb.FallDetection.repository.FallEventRepository;
import com.sb.FallDetection.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/fall")
public class FallDetectionController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private FallEventRepository fallEventRepository;

    private static final double FALL_THRESHOLD = 2.5; // example acceleration threshold

    @PostMapping("/detect")
    public String detectFall(@RequestBody FallEvent event) {
        // Save event
        event.setTimestamp(LocalDateTime.now());
        fallEventRepository.save(event);

        // Check threshold and send email alert
        if(event.getAcceleration() > FALL_THRESHOLD) {

            // Replace with actual recipient email
            emailService.sendFallAlert(
                    new String[]{"bandigisaikumar@gmail.com"},
                    event.getDeviceId(),
                    event.getAcceleration()
            );

            return "Fall detected and alert sent!";
        }
        return "No fall detected.";
    }

}
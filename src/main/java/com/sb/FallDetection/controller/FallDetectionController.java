package com.sb.FallDetection.controller;

import com.sb.FallDetection.model.FallEvent;
import com.sb.FallDetection.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/fall")
public class FallDetectionController {

    @Autowired
    private EmailService emailService;

    private static final double FALL_THRESHOLD = 2.5;

    @PostMapping("/detect")
    public String detectFall(@RequestBody FallEvent event) {
        event.setTimestamp(LocalDateTime.now());

        if (event.getAcceleration() > FALL_THRESHOLD) {
            emailService.sendFallAlert(
                    new String[]{"bandigisaikumar@gmail.com"},  // Email recipients
                    event.getDeviceId(),
                    event.getAcceleration()
            );

            return "Fall detected and alert sent!";
        }
        return "No fall detected.";
    }
}

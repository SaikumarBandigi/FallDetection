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

    @PostMapping("/detect")
    public String detectFall(@RequestBody FallEvent event) {
        event.setTimestamp(LocalDateTime.now());

        emailService.sendFallAlert(
                new String[]{"bandigisaikumar@gmail.com"},  // Replace with desired recipients
                event.getDeviceId(),
                event.getAcceleration()
        );

        return "Fall alert received and email sent!";
    }
}

package com.sb.FallDetection.controller;

import com.sb.FallDetection.model.FallEvent;
import com.sb.FallDetection.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/fall")
public class FallDetectionController {

    @Autowired
    private AlertService alertService;

    private static final double FALL_THRESHOLD = 2.5;

    @PostMapping("/detect")
    public String detectFall(@RequestBody FallEvent event) {
        event.setTimestamp(LocalDateTime.now());

        if (event.getAcceleration() > FALL_THRESHOLD) {
            // Send alerts (email + WhatsApp)
            alertService.sendFallAlert(
                    new String[]{"bandigisaikumar@gmail.com"},  // Email recipients
                    "+916305032504",                             // WhatsApp number in E.164 format (with country code, no plus)
                    event.getDeviceId(),
                    event.getAcceleration()
            );

            return "Fall detected and alert sent!";
        }
        return "No fall detected.";
    }

}

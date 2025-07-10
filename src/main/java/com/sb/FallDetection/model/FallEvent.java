package com.sb.FallDetection.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FallEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private double acceleration;
    private LocalDateTime timestamp;

    public FallEvent() {}

    public FallEvent(String deviceId, double acceleration, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.acceleration = acceleration;
        this.timestamp = timestamp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FallEvent{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", acceleration=" + acceleration +
                ", timestamp=" + timestamp +
                '}';
    }
}

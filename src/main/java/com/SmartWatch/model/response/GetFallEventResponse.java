package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetFallEventResponse {
    private long id;
    private long userId;
    private String deviceId;
    private String severity;
    private int spo2;
    private int heartRate;
    private double longitude;
    private double latitude;
    private String status;
    private LocalDateTime detectedAt;
}

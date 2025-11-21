package com.SmartWatch.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaveRecordRequest {
    private String deviceId;
    private int spo2;
    private int heartRate;
    private String signalQuality;
    private int battery;
    private LocalDateTime recordedAt;
}

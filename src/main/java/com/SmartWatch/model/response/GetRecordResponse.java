package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetRecordResponse {
    private long id;
    private long userId;
    private String deviceId;
    private int spo2;
    private int heartRate;
    private String signalQuality;
    private int battery;
    private LocalDateTime recordedAt;
    private LocalDateTime syncedAt;
}

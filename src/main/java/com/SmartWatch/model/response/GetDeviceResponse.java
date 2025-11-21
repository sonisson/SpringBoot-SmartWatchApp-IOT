package com.SmartWatch.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetDeviceResponse {
    private String id;
    private String name;
    private String macAddress;
    private long ownerId;
    private int battery;
    private LocalDateTime lastSync;
    private boolean isOnline;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
}

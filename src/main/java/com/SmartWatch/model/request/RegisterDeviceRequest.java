package com.SmartWatch.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDeviceRequest {
    String id;
    String name;
    String macAddress;
}

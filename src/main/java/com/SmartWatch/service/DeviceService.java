package com.SmartWatch.service;

import com.SmartWatch.converter.DeviceConverter;
import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterDeviceRequest;
import com.SmartWatch.repository.DeviceRepository;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceConverter deviceConverter;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerDevice(RegisterDeviceRequest request, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        DeviceEntity deviceEntity = deviceConverter.toDeviceEntity(request, userEntity);
        deviceEntity = deviceRepository.save(deviceEntity);
        return ResponseEntity.status(201).body(deviceConverter.toGetDeviceResponse(deviceEntity));
    }

    public ResponseEntity<?> getDevices(String username) {
        List<DeviceEntity> deviceEntityList = deviceRepository.findByUserEntityUsername(username);
        return ResponseEntity.ok(deviceConverter.toGetDeviceresponseList(deviceEntityList));
    }

}

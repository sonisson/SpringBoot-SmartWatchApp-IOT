package com.SmartWatch.controller;

import com.SmartWatch.model.request.RegisterDeviceRequest;
import com.SmartWatch.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register-device")
    public ResponseEntity<?> registerDevice(@RequestBody RegisterDeviceRequest request, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return deviceService.registerDevice(request, username);
    }

    @GetMapping("/get-devices")
    public ResponseEntity<?> getDevice(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return deviceService.getDevices(username);
    }

}

package com.SmartWatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @GetMapping("/test-connection")
    public ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Kết nối thành công.");
    }

    @GetMapping("/test-login")
    public ResponseEntity<?> testLogin() {
        return ResponseEntity.ok("Đăng nhập thành công.");
    }

    @GetMapping("/test-user")
    public ResponseEntity<?> testUserApi() {
        return ResponseEntity.ok("Đăng nhập thành công với quyền user.");
    }
}

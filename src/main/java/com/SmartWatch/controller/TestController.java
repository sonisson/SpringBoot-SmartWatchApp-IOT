package com.SmartWatch.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<?> testConnection() {
        return ResponseEntity.ok("Kết nối thành công");
    }

    @GetMapping("/test/login")
    public ResponseEntity<?> testLogin() {
        return ResponseEntity.ok("Bạn đã đăng nhập.");
    }

    @GetMapping("/test/admin")
    public ResponseEntity<?> testAdminApi() {
        return ResponseEntity.ok("Bạn đã đăng nhập với quyền admin.");
    }

    @GetMapping("/test/user")
    public ResponseEntity<?> testUserApi() {
        return ResponseEntity.ok("Bạn đã đăng nhập với quyền user.");
    }

    @GetMapping("/test/watcher")
    public ResponseEntity<?> testWatchApi() {
        return ResponseEntity.ok("Bạn đã đăng nhập với quyền watcher.");
    }
}

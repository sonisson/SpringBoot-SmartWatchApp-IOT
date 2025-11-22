package com.SmartWatch.controller;

import com.SmartWatch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search-user")
    public ResponseEntity<?> getUsers(@RequestParam("username") String username) {
        return userService.getUsers(username);
    }
}

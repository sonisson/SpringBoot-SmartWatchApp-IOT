package com.SmartWatch.controller;

import com.SmartWatch.model.request.LoginRequest;
import com.SmartWatch.model.request.RegisterRequest;
import com.SmartWatch.model.response.ErrorResponse;
import com.SmartWatch.repository.UserRepository;
import com.SmartWatch.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(userService.login(loginRequest.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ErrorResponse("INVALID_CREDENTIALS", "Tên đăng nhập hoặc mật khẩu không đúng."));
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest, HttpServletResponse response) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.status(409).body(new ErrorResponse("USER_EXISTS", "Username đã tồn tại."));
        }
        return ResponseEntity.ok(userService.register(registerRequest));
    }
}

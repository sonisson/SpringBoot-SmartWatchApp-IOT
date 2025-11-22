package com.SmartWatch.service;

import com.SmartWatch.model.response.ErrorResponse;
import com.SmartWatch.converter.UserConverter;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterRequest;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.status(409).body(new ErrorResponse("USER_EXISTS", "Username đã tồn tại."));
        }
        UserEntity userEntity = userConverter.toUserEntity(registerRequest);
        userEntity = userRepository.save(userEntity);
        return ResponseEntity.status(201).body(userConverter.toGetUserResponse(userEntity));
    }

    public ResponseEntity<?> login(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return ResponseEntity.ok(userConverter.toLoginResponse(userEntity));
    }

    public ResponseEntity<?> getUsers(String username) {
        List<UserEntity> userEntityList = userRepository.findByUsernameContainingIgnoreCase(username);
        return ResponseEntity.ok(userConverter.toGetUserResponseList(userEntityList));
    }
}

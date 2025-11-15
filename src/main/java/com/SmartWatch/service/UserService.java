package com.SmartWatch.service;

import com.SmartWatch.model.response.LoginResponse;
import com.SmartWatch.model.response.RegisterResponse;
import com.SmartWatch.converter.UserConverter;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterRequest;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public RegisterResponse register(RegisterRequest registerRequest) {
        UserEntity userEntity = userConverter.toUserEntity(registerRequest);
        userEntity = userRepository.save(userEntity);
        return userConverter.toRegisterResponse(userEntity);
    }

    public LoginResponse login(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userConverter.toLoginResponse(userEntity);
    }

}

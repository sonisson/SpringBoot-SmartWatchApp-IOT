package com.SmartWatch.converter;

import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterRequest;
import com.SmartWatch.model.response.GetUserResponse;
import com.SmartWatch.model.response.LoginResponse;
import com.SmartWatch.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;

    public UserEntity toUserEntity(RegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setName(registerRequest.getName());
        userEntity.setRole("USER");
        userEntity.setActive(true);
        return userEntity;
    }

    public LoginResponse toLoginResponse(UserEntity userEntity) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(tokenUtil.generateJwt(userEntity));
        loginResponse.setUser(toGetUserResponse(userEntity));
        return loginResponse;
    }

    public GetUserResponse toGetUserResponse(UserEntity userEntity) {
        GetUserResponse response = new GetUserResponse();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getUsername());
        response.setRole(userEntity.getRole());
        response.setName(userEntity.getName());
        response.setCreateAt(userEntity.getCreatedAt());
        response.setUpdateAt(userEntity.getUpdatedAt());
        response.setActive(userEntity.isActive());
        return response;
    }
}

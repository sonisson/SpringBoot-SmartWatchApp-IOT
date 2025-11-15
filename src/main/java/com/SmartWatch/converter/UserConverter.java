package com.SmartWatch.converter;

import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterRequest;
import com.SmartWatch.model.response.LoginResponse;
import com.SmartWatch.model.response.RegisterResponse;
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

    public UserEntity toUserEntity(RegisterRequest registerRequest){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userEntity.setName(registerRequest.getName());
        userEntity.setRole(registerRequest.getRole());
        return userEntity;
    }

    public RegisterResponse toRegisterResponse(UserEntity userEntity){
        RegisterResponse registerResponse=new RegisterResponse();
        RegisterResponse.Data data=new RegisterResponse.Data();
        RegisterResponse.Data.User user=new RegisterResponse.Data.User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setName(userEntity.getName());
        user.setRole(userEntity.getRole());
        data.setUser(user);
        registerResponse.setData(data);
        return registerResponse;
    }

    public LoginResponse toLoginResponse(UserEntity userEntity){
        LoginResponse loginResponse=new LoginResponse();
        LoginResponse.Data data=new LoginResponse.Data();
        data.setToken(tokenUtil.generateJwt(userEntity));
        LoginResponse.Data.User user=new LoginResponse.Data.User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setName(userEntity.getName());
        user.setRole(userEntity.getRole());
        data.setUser(user);
        loginResponse.setData(data);
        return loginResponse;
    }
}

package com.SmartWatch.converter;

import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.entity.UserRelationEntity;
import com.SmartWatch.model.response.GetUserRelationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRelationConverter {

    @Autowired
    private UserConverter userConverter;

    public GetUserRelationResponse toGetUserRelationResponse(UserRelationEntity userRelationEntity) {
        GetUserRelationResponse response = new GetUserRelationResponse();
        response.setId(userRelationEntity.getId());
        response.setPatient(userConverter.toGetUserResponse(userRelationEntity.getPatientUserEntity()));
        response.setCare(userConverter.toGetUserResponse(userRelationEntity.getCareUserEntity()));
        response.setStatus(userRelationEntity.getStatus());
        response.setCreatedAt(userRelationEntity.getCreatedAt());
        response.setUpdatedAt(userRelationEntity.getUpdatedAt());
        return response;
    }

    public List<GetUserRelationResponse> toGetUserRelationResponseList(List<UserRelationEntity> userRelationEntityList) {
        List<GetUserRelationResponse> responseList = new ArrayList<>();
        for (UserRelationEntity userRelationEntity : userRelationEntityList) {
            GetUserRelationResponse response = toGetUserRelationResponse(userRelationEntity);
            responseList.add(response);
        }
        return responseList;
    }

    public UserRelationEntity toUserRelationEntity(UserEntity careUserEntity, UserEntity patientUserEntity) {
        UserRelationEntity userRelationEntity = new UserRelationEntity();
        userRelationEntity.setCareUserEntity(careUserEntity);
        userRelationEntity.setPatientUserEntity(patientUserEntity);
        return userRelationEntity;
    }
}

package com.SmartWatch.converter;

import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.FallEventEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.SaveFallEventRequest;
import com.SmartWatch.model.response.GetFallEventResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FallEventConverter {
    public GetFallEventResponse toGetFallEventResponse(FallEventEntity fallEventEntity) {
        GetFallEventResponse response = new GetFallEventResponse();
        response.setId(fallEventEntity.getId());
        response.setUserId(fallEventEntity.getUserEntity().getId());
        response.setDeviceId(fallEventEntity.getDeviceEntity().getId());
        response.setSeverity(fallEventEntity.getSeverity());
        response.setSpo2(fallEventEntity.getSpo2());
        response.setHeartRate(fallEventEntity.getHeartRate());
        response.setLongitude(fallEventEntity.getLongitude());
        response.setLatitude(fallEventEntity.getLatitude());
        response.setStatus(fallEventEntity.getStatus());
        response.setDetectedAt(fallEventEntity.getDetectedAt());
        return response;
    }

    public List<GetFallEventResponse> toGetFallEventResponseList(List<FallEventEntity> fallEventEntityList) {
        List<GetFallEventResponse> responseList = new ArrayList<>();
        for (FallEventEntity fallEventEntity : fallEventEntityList) {
            GetFallEventResponse response = toGetFallEventResponse(fallEventEntity);
            responseList.add(response);
        }
        return responseList;
    }

    public FallEventEntity toFallEventEntity(SaveFallEventRequest request, UserEntity userEntity, DeviceEntity deviceEntity) {
        FallEventEntity fallEventEntity = new FallEventEntity();
        fallEventEntity.setUserEntity(userEntity);
        fallEventEntity.setDeviceEntity(deviceEntity);
        fallEventEntity.setSeverity(request.getSeverity());
        fallEventEntity.setSpo2(request.getSpo2());
        fallEventEntity.setHeartRate(request.getHeartRate());
        fallEventEntity.setLongitude(request.getLongitude());
        fallEventEntity.setLatitude(request.getLatitude());
        fallEventEntity.setStatus(request.getStatus());
        fallEventEntity.setDetectedAt(request.getDetectedAt());
        return fallEventEntity;
    }
}

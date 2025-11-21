package com.SmartWatch.converter;

import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.RegisterDeviceRequest;
import com.SmartWatch.model.response.GetDeviceResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceConverter {
    public DeviceEntity toDeviceEntity(RegisterDeviceRequest request, UserEntity userEntity) {
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(request.getId());
        deviceEntity.setName(request.getName());
        deviceEntity.setMacAddress(request.getMacAddress());
        deviceEntity.setUserEntity(userEntity);
        return deviceEntity;
    }

    public GetDeviceResponse toGetDeviceResponse(DeviceEntity deviceEntity) {
        GetDeviceResponse response = new GetDeviceResponse();
        response.setId(deviceEntity.getId());
        response.setName(deviceEntity.getName());
        response.setMacAddress(deviceEntity.getMacAddress());
        response.setOwnerId(deviceEntity.getUserEntity().getId());
        response.setBattery(deviceEntity.getBattery());
        response.setLastSync(deviceEntity.getLastSync());
        response.setOnline(deviceEntity.isOnline());
        response.setCreatedAt(deviceEntity.getCreatedAt());
        response.setUpdatedAt(deviceEntity.getUpdatedAt());
        return response;
    }

    public List<GetDeviceResponse> toGetDeviceresponseList(List<DeviceEntity> deviceEntityList) {
        List<GetDeviceResponse> responseList = new ArrayList<>();
        for (DeviceEntity deviceEntity : deviceEntityList) {
            GetDeviceResponse response = toGetDeviceResponse(deviceEntity);
            responseList.add(response);
        }
        return responseList;
    }
}

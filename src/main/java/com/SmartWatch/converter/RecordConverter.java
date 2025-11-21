package com.SmartWatch.converter;

import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.RecordEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.SaveRecordRequest;
import com.SmartWatch.model.response.GetRecordResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordConverter {

    public GetRecordResponse toGetRecordResponse(RecordEntity recordEntity) {
        GetRecordResponse response = new GetRecordResponse();
        response.setId(recordEntity.getId());
        response.setUserId(recordEntity.getUserEntity().getId());
        response.setDeviceId(recordEntity.getDeviceEntity().getId());
        response.setSpo2(recordEntity.getSpo2());
        response.setHeartRate(recordEntity.getHeartRate());
        response.setSignalQuality(recordEntity.getSignalQuality());
        response.setBattery(recordEntity.getBattery());
        response.setRecordedAt(recordEntity.getRecordedAt());
        response.setSyncedAt(recordEntity.getSyncedAt());
        return response;
    }

    public List<GetRecordResponse> toGetRecordResponseList(List<RecordEntity> recordEntityList) {
        List<GetRecordResponse> responseList = new ArrayList<>();
        for (RecordEntity recordEntity : recordEntityList) {
            GetRecordResponse response = toGetRecordResponse(recordEntity);
            responseList.add(response);
        }
        return responseList;
    }

    public RecordEntity toRecordEntity(SaveRecordRequest request, UserEntity userEntity, DeviceEntity deviceEntity) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setUserEntity(userEntity);
        recordEntity.setDeviceEntity(deviceEntity);
        recordEntity.setSpo2(request.getSpo2());
        recordEntity.setHeartRate(request.getHeartRate());
        recordEntity.setSignalQuality(request.getSignalQuality());
        recordEntity.setBattery(request.getBattery());
        recordEntity.setRecordedAt(request.getRecordedAt());
        return recordEntity;
    }
}

package com.SmartWatch.service;

import com.SmartWatch.converter.RecordConverter;
import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.RecordEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.SaveRecordRequest;
import com.SmartWatch.model.response.ErrorResponse;
import com.SmartWatch.repository.DeviceRepository;
import com.SmartWatch.repository.RecordRepository;
import com.SmartWatch.repository.UserRelationRepository;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordConverter recordConverter;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<?> getRecords(String careUsername, String patientUsername) {
        boolean followed = careUsername.equals(patientUsername) || userRelationRepository.existsByCareUserEntityUsernameAndPatientUserEntityUsernameAndStatus(careUsername, patientUsername, "accepted");
        if (!followed)
            return ResponseEntity.status(403).body(new ErrorResponse("FORBIDDEN", "Không có quyền truy cập."));
        List<RecordEntity> recordEntityList = recordRepository.findTop10ByUserEntityUsernameOrderByRecordedAtDesc(patientUsername);
        return ResponseEntity.ok(recordConverter.toGetRecordResponseList(recordEntityList));
    }

    public ResponseEntity<?> saveRecord(SaveRecordRequest request, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(request.getDeviceId());
        DeviceEntity deviceEntity = null;
        if (deviceEntityOptional.isPresent()) deviceEntity = deviceEntityOptional.get();
        RecordEntity recordEntity = recordConverter.toRecordEntity(request, userEntity, deviceEntity);
        recordEntity = recordRepository.save(recordEntity);
        return ResponseEntity.status(201).body(recordConverter.toGetRecordResponse(recordEntity));
    }
}

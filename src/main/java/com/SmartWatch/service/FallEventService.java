package com.SmartWatch.service;

import com.SmartWatch.converter.FallEventConverter;
import com.SmartWatch.entity.DeviceEntity;
import com.SmartWatch.entity.FallEventEntity;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.model.request.SaveFallEventRequest;
import com.SmartWatch.model.response.ErrorResponse;
import com.SmartWatch.model.response.GetFallEventResponse;
import com.SmartWatch.model.response.SocketResponse;
import com.SmartWatch.repository.DeviceRepository;
import com.SmartWatch.repository.FallEventRepository;
import com.SmartWatch.repository.UserRelationRepository;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FallEventService {

    @Autowired
    private FallEventRepository fallEventRepository;

    @Autowired
    private FallEventConverter fallEventConverter;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    private final SimpMessagingTemplate messagingTemplate;

    public FallEventService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public ResponseEntity<?> getFallEvents(String careUsername, String patientUsername) {
        boolean followed = careUsername.equals(patientUsername) || userRelationRepository.existsByCareUserEntityUsernameAndPatientUserEntityUsernameAndStatus(careUsername, patientUsername, "accepted");
        if (!followed)
            return ResponseEntity.status(403).body(new ErrorResponse("FORBIDDEN", "Không có quyền truy cập."));
        List<FallEventEntity> fallEventEntityList = fallEventRepository.findTop10ByUserEntityUsernameOrderByDetectedAtDesc(patientUsername);
        return ResponseEntity.ok(fallEventConverter.toGetFallEventResponseList(fallEventEntityList));
    }

    public ResponseEntity<?> saveFallEvent(SaveFallEventRequest request, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        DeviceEntity deviceEntity = deviceRepository.findById(request.getDeviceId()).orElse(null);
        FallEventEntity fallEventEntity = fallEventConverter.toFallEventEntity(request, userEntity, deviceEntity);
        fallEventEntity = fallEventRepository.save(fallEventEntity);
        GetFallEventResponse response = fallEventConverter.toGetFallEventResponse(fallEventEntity);
        messagingTemplate.convertAndSend("/topic/patient/" + username, new SocketResponse<>("FALL_EVENT", response));
        return ResponseEntity.status(201).body(response);
    }

}

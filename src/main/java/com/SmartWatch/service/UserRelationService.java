package com.SmartWatch.service;

import com.SmartWatch.converter.UserRelationConverter;
import com.SmartWatch.entity.UserEntity;
import com.SmartWatch.entity.UserRelationEntity;
import com.SmartWatch.model.request.AcceptRequest;
import com.SmartWatch.model.request.InviteRequest;
import com.SmartWatch.model.request.RejectRequest;
import com.SmartWatch.model.response.ErrorResponse;
import com.SmartWatch.repository.UserRelationRepository;
import com.SmartWatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationService {

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private UserRelationConverter userRelationConverter;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> getFollowers(String username) {
        List<UserRelationEntity> userRelationEntityList = userRelationRepository.findByPatientUserEntityUsername(username);
        return ResponseEntity.ok(userRelationConverter.toGetUserRelationResponseList(userRelationEntityList));
    }

    public ResponseEntity<?> getFollowing(String username) {
        List<UserRelationEntity> userRelationEntityList = userRelationRepository.findByCareUserEntityUsername(username);
        return ResponseEntity.ok(userRelationConverter.toGetUserRelationResponseList(userRelationEntityList));
    }

    public ResponseEntity<?> invite(InviteRequest request, String careUsername) {
        String patientUsername = request.getUsername();
        if(careUsername.equals(patientUsername)) return ResponseEntity.status(409).body(new ErrorResponse("CONFLICT", "Không thể tự mời."));
        UserRelationEntity userRelationEntity = userRelationRepository.findByCareUserEntityUsernameAndPatientUserEntityUsername(careUsername, patientUsername);
        if (userRelationEntity == null) {
            UserEntity careUserEntity = userRepository.findByUsername(careUsername);
            UserEntity patientUserEntity = userRepository.findByUsername(patientUsername);
            if (careUserEntity == null || patientUserEntity == null)
                return ResponseEntity.status(404).body(new ErrorResponse("NOT_FOUND", "User không tồn tại."));
            userRelationEntity = userRelationConverter.toUserRelationEntity(careUserEntity, patientUserEntity);
        }
        userRelationEntity.setStatus("pending");
        userRelationEntity = userRelationRepository.save(userRelationEntity);
        return ResponseEntity.ok(userRelationConverter.toGetUserRelationResponse(userRelationEntity));
    }

    public ResponseEntity<?> accept(AcceptRequest request, String username) {
        UserRelationEntity userRelationEntity = userRelationRepository.findById(request.getUserRelationId()).orElse(null);
        if (userRelationEntity != null) {
            if (!userRelationEntity.getPatientUserEntity().getUsername().equals(username)) {
                return ResponseEntity.status(401).body(new ErrorResponse("FORBIDDEN", "Không có quyền truy cập"));
            }
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse("NOT_FOUND", "Dữ liệu không tồn tại"));
        }
        userRelationEntity.setStatus("accepted");
        userRelationEntity = userRelationRepository.save(userRelationEntity);
        return ResponseEntity.ok(userRelationConverter.toGetUserRelationResponse(userRelationEntity));
    }

    public ResponseEntity<?> reject(RejectRequest request, String username) {
        UserRelationEntity userRelationEntity = userRelationRepository.findById(request.getUserRelationId()).orElse(null);
        if (userRelationEntity != null) {
            if (!userRelationEntity.getPatientUserEntity().getUsername().equals(username)) {
                return ResponseEntity.status(401).body(new ErrorResponse("FORBIDDEN", "Không có quyền truy cập"));
            }
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse("NOT_FOUND", "Dữ liệu không tồn tại"));
        }
        userRelationEntity.setStatus("rejected");
        userRelationEntity = userRelationRepository.save(userRelationEntity);
        return ResponseEntity.ok(userRelationConverter.toGetUserRelationResponse(userRelationEntity));
    }

}

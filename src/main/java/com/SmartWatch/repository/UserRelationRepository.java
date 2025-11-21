package com.SmartWatch.repository;

import com.SmartWatch.entity.UserRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelationEntity, Long> {
    boolean existsByCareUserEntityUsernameAndPatientUserEntityUsernameAndStatus(String careUsername, String patientUsername, String status);

    List<UserRelationEntity> findByPatientUserEntityUsername(String username);

    List<UserRelationEntity> findByCareUserEntityUsername(String username);

    UserRelationEntity findByCareUserEntityUsernameAndPatientUserEntityUsername(String careUsername, String patientUsername);
}
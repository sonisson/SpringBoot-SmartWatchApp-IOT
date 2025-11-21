package com.SmartWatch.repository;

import com.SmartWatch.entity.FallEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FallEventRepository extends JpaRepository<FallEventEntity, Long> {
    List<FallEventEntity> findByUserEntityUsername(String username);
}
package com.SmartWatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "device")
@Getter
@Setter
public class DeviceEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "battery")
    private int battery;

    @Column(name = "last_sync")
    private LocalDateTime lastSync;

    @Column(name = "is_online")
    private boolean isOnline;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "deviceEntity")
    private List<RecordEntity> recordEntityList;

    @OneToMany(mappedBy = "deviceEntity")
    private List<FallEventEntity> fallEventEntityList;

}

package com.SmartWatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "userEntity")
    private List<DeviceEntity> deviceEntityList;

    @OneToMany(mappedBy = "careUserEntity")
    private List<UserRelationEntity> patientRelationEntityList;

    @OneToMany(mappedBy = "patientUserEntity")
    private List<UserRelationEntity> careRelationEntityList;

    @OneToMany(mappedBy = "userEntity")
    private List<RecordEntity> recordEntityList;

    @OneToMany(mappedBy = "userEntity")
    private List<FallEventEntity> fallEventEntityList;
}

package com.SmartWatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fall_event")
@Getter
@Setter
public class FallEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "severity")
    private String severity;

    @Column(name = "spo2")
    private int spo2;

    @Column(name = "heart_rate")
    private int heartRate;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "status")
    private String status;

    @Column(name = "detected_at")
    private LocalDateTime detectedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity deviceEntity;
}

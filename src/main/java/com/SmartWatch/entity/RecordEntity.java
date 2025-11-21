package com.SmartWatch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@Getter
@Setter
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "spo2")
    private int spo2;

    @Column(name = "heart_rate")
    private int heartRate;

    @Column(name = "signal_quality")
    private String signalQuality;

    @Column(name = "battery")
    private int battery;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    @Column(name = "synced_at")
    private LocalDateTime syncedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity deviceEntity;
}

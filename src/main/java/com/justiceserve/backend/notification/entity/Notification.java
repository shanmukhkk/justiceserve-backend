package com.justiceserve.backend.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long userId;

    private Long entityId;

    @Column(nullable = false, length = 500)
    private String message;

    private String category;

    private String status;

    private LocalDateTime createdDate;
}
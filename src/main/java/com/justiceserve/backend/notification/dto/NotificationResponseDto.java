package com.justiceserve.backend.notification.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponseDto {

    private Long notificationId;
    private Long userId;
    private Long entityId;
    private String message;
    private String category;
    private String status;
    private LocalDateTime createdDate;
}
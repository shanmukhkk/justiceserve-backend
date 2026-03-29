package com.justiceserve.backend.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationRequestDto {

    private Long userId;
    private Long entityId;

    @NotBlank(message = "Message is required")
    private String message;

    private String category;
    private String status;
    private LocalDateTime createdDate;
}
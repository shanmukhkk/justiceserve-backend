package com.justiceserve.backend.notification.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationStatusUpdateDto {

    @NotBlank(message = "Status is required")
    private String status;
}
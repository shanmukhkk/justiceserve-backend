package com.justiceserve.backend.notification.controller;

import com.justiceserve.backend.notification.dto.NotificationRequestDto;
import com.justiceserve.backend.notification.dto.NotificationResponseDto;
import com.justiceserve.backend.notification.dto.NotificationStatusUpdateDto;
import com.justiceserve.backend.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationResponseDto createNotification(@Valid @RequestBody NotificationRequestDto dto) {
        return notificationService.createNotification(dto);
    }

    @GetMapping
    public List<NotificationResponseDto> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationResponseDto getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @GetMapping("/user/{userId}")
    public List<NotificationResponseDto> getNotificationsByUserId(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @PutMapping("/{id}/status")
    public NotificationResponseDto updateNotificationStatus(@PathVariable Long id,
                                                            @Valid @RequestBody NotificationStatusUpdateDto dto) {
        return notificationService.updateNotificationStatus(id, dto.getStatus());
    }
}
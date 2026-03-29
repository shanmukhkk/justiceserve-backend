package com.justiceserve.backend.notification.service;

import com.justiceserve.backend.common.exception.ResourceNotFoundException;
import com.justiceserve.backend.notification.dto.NotificationRequestDto;
import com.justiceserve.backend.notification.dto.NotificationResponseDto;
import com.justiceserve.backend.notification.entity.Notification;
import com.justiceserve.backend.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponseDto createNotification(NotificationRequestDto dto) {
        Notification notification = Notification.builder()
                .userId(dto.getUserId())
                .entityId(dto.getEntityId())
                .message(dto.getMessage())
                .category(dto.getCategory())
                .status(dto.getStatus() != null ? dto.getStatus() : "UNREAD")
                .createdDate(dto.getCreatedDate() != null ? dto.getCreatedDate() : LocalDateTime.now())
                .build();

        Notification savedNotification = notificationRepository.save(notification);
        return mapToResponse(savedNotification);
    }

    public List<NotificationResponseDto> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));

        return mapToResponse(notification);
    }

    public List<NotificationResponseDto> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public NotificationResponseDto updateNotificationStatus(Long id, String status) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));

        notification.setStatus(status);

        Notification updatedNotification = notificationRepository.save(notification);
        return mapToResponse(updatedNotification);
    }

    private NotificationResponseDto mapToResponse(Notification notification) {
        return NotificationResponseDto.builder()
                .notificationId(notification.getNotificationId())
                .userId(notification.getUserId())
                .entityId(notification.getEntityId())
                .message(notification.getMessage())
                .category(notification.getCategory())
                .status(notification.getStatus())
                .createdDate(notification.getCreatedDate())
                .build();
    }
}
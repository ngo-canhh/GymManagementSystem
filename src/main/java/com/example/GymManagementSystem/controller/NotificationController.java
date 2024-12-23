package com.example.GymManagementSystem.controller;

import com.example.GymManagementSystem.DTO.NotificationDTO;
import com.example.GymManagementSystem.entity.Notification;
import com.example.GymManagementSystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{staffId}")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@PathVariable int staffId) {
        List<Notification> notifications = notificationService.getNotificationsForStaff(staffId);
        List<NotificationDTO> notificationDTOs = notifications.stream()
                .map(notification -> new NotificationDTO(
                        notification.getId(),
                        notification.getMessage(),
                        notification.isRead(),
                        notification.getCreatedAt().toString()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationDTOs);
    }

    @PostMapping("/read/{notificationId}")
    public ResponseEntity<String> markAsRead(@PathVariable Integer notificationId) {
        notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok("Notification marked as read");
    }

    @PutMapping("/{id}/mark-as-read")
    public ResponseEntity<Void> markAsRead(@PathVariable int id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
package com.example.GymManagementSystem.service;

import com.example.GymManagementSystem.entity.Notification;
import com.example.GymManagementSystem.repository.NotificationRepository;
import com.example.GymManagementSystem.entity.StaffLogin;
import com.example.GymManagementSystem.repository.StaffLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private StaffLoginRepository staffLoginRepository;

    public void sendNotification(int staffId, String message) {
       Optional<StaffLogin> staffLoginOptional = staffLoginRepository.findById(staffId);

        staffLoginOptional.ifPresent(staffLogin -> {
            Notification notification = new Notification();
            notification.setId(staffId); 
            notification.setMessage(message);
            notification.setRead(false);
            notificationRepository.save(notification);
        });
    }

    public List<Notification> getNotificationsForStaff(int staffId) {
        return notificationRepository.findByStaffIdOrderByCreatedAtDesc(staffId);
    }

    public void markNotificationAsRead(Integer notificationId) {
        notificationRepository.findById(notificationId)
                .ifPresent(notification -> {
                    notification.setRead(true);
                    notificationRepository.save(notification);
                });
    }

    public void markAsRead(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
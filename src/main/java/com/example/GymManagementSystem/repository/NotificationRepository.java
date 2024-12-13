package com.example.GymManagementSystem.repository;

import com.example.GymManagementSystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
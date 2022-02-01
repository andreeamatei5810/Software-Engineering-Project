package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,String> {
}

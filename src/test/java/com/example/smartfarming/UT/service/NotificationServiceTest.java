package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.entity.Notification;
import com.example.smartfarming.repository.NotificationRepository;
import com.example.smartfarming.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testGetAllNotifications() throws Exception {



    }


}

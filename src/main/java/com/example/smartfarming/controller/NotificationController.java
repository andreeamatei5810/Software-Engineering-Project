package com.example.smartfarming.controller;

import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    final NotificationService notificationService;

    @GetMapping
    public NotificationsListDto getAllNotifications(){
        return notificationService.getAllNotifications();
    }

}

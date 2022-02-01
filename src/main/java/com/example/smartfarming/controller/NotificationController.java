package com.example.smartfarming.controller;

import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    final NotificationService notificationService;

    @GetMapping
    public NotificationsListDto getAllNotifications(Principal principal){
        return notificationService.getAllNotifications(principal.getName());
    }

}

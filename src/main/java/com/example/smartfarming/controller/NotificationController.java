package com.example.smartfarming.controller;

import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    final NotificationService notificationService;

    @PostMapping("/checkWeather")
    public NotificationDto getWeather(@RequestParam String country, @RequestParam String city){
        return notificationService.sendNotifications(country,city);
    }

    @GetMapping
    public List<NotificationDto> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

}

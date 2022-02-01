package com.example.smartfarming.service;

import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.entity.Notification;
import com.example.smartfarming.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class NotificationService {

    final String extremeWeatherMessage = "It is necessary to protect the crop so that there is no damage.";
    final NotificationRepository notificationRepository;
    final WeatherService weatherService;


    public NotificationDto sendNotifications(String country, String city){
        CurrentWeather currentWeather = weatherService.getWeather(country, city);
        if(currentWeather.getWeatherDescription().compareTo("Clear") != 0){
            String cityWeather = "Weather in " + city + ": " + currentWeather.getWeatherDescription().toLowerCase() + ". "
                     + extremeWeatherMessage;
            Notification notification = new Notification().setId(UUID.randomUUID().toString())
                    .setTimeStamp(LocalDateTime.now()).setMessage(cityWeather).setRead(false);
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationRepository.save(notification);
            return notificationDto;
        }
        return null;
    }


    public NotificationsListDto getAllNotifications(){
        List<NotificationDto> notificationDtos = new ArrayList<>();
        AtomicInteger newNotifications = new AtomicInteger();
        notificationRepository.findAll().forEach(notification -> {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            if(!notification.getRead()){
                newNotifications.set(newNotifications.get() + 1);
                notification.setRead(true);
                notificationRepository.save(notification);
            }
            notificationDtos.add(notificationDto);
        });
        NotificationsListDto notificationsListDto = new NotificationsListDto();
        notificationsListDto.setNumberOfNewMessages(newNotifications.get());
        notificationsListDto.setNotifications(notificationDtos);
        return notificationsListDto;
    }

}

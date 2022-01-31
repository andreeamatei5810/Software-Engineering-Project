package com.example.smartfarming.service;

import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.entity.Notification;
import com.example.smartfarming.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    final String extremeWeatherMessage = "It is necessary to protect the crop so that there is no damage.";
    final NotificationRepository notificationRepository;
    final WeatherService weatherService;


    public NotificationDto sendNotifications(String country, String city){
        CurrentWeather currentWeather = weatherService.getWeather(country, city);
        System.out.println(currentWeather.getWeatherDescription());
        System.out.println(currentWeather.getWeatherDescription().compareTo("Clear") != 0);
        if(currentWeather.getWeatherDescription().compareTo("Clear") != 0){
            String cityWeather = "Weather in " + city + ": " + currentWeather.getWeatherDescription().toLowerCase() + ". "
                     + extremeWeatherMessage;
            Notification notification = new Notification().setId(UUID.randomUUID().toString())
                    .setTimeStamp(LocalDateTime.now()).setMessage(cityWeather);
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationRepository.save(notification);
            return notificationDto;
        }
        return null;
    }


    public List<NotificationDto> getAllNotifications(){
        List<NotificationDto> notificationDtos = new ArrayList<>();
        notificationRepository.findAll().forEach(notification -> {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDtos.add(notificationDto);
        });
        return notificationDtos;
    }

}

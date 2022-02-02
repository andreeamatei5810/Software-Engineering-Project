package com.example.smartfarming.service;

import com.example.smartfarming.SmartFarmingApplication;
import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.NotificationDto;
import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.entity.Client;
import com.example.smartfarming.entity.Notification;
import com.example.smartfarming.exception.CustomException;
import com.example.smartfarming.repository.ClientRepository;
import com.example.smartfarming.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class NotificationService {

    final String extremeWeatherMessage = "It is necessary to protect the crop so that there is no damage.";
    final NotificationRepository notificationRepository;
    final WeatherService weatherService;
    final ClientRepository clientRepository;

    @Scheduled(fixedRate = 60*5000, initialDelay = 60*1000)
    public void sendNotifications(){
        clientRepository.findAll().forEach(client -> {
            CurrentWeather currentWeather = weatherService.getWeather(client.getEmail());
            if (currentWeather.getWeatherDescription().compareTo("Clear") != 0) {
                String cityWeather = "Weather in " + client.getCity() +": " + currentWeather.getWeatherDescription().toLowerCase() + ". "
                        + extremeWeatherMessage;
                Notification notification = new Notification().setId(UUID.randomUUID().toString())
                        .setTimeStamp(LocalDateTime.now()).setMessage(cityWeather).setRead(false).setClientId(client.getId());
                NotificationDto notificationDto = new NotificationDto();
                BeanUtils.copyProperties(notification, notificationDto);
                notificationRepository.save(notification);
            }
        }
        );
    }


    public NotificationsListDto getAllNotifications(){
        Client currentUser = SmartFarmingApplication.getCurrentUser();
        if(currentUser == null) {
            throw new CustomException("You need to be logged in to do this operation!");
        }
        Optional<Client> client = clientRepository.findByEmail(currentUser.getEmail());
        List<NotificationDto> notificationDtos = new ArrayList<>();
        NotificationsListDto notificationsListDto = new NotificationsListDto();
        if(client.isPresent()) {
            AtomicInteger newNotifications = new AtomicInteger();
            notificationRepository.findAll().forEach(notification -> {
                if(notification.getClientId().equals(client.get().getId())) {
                    NotificationDto notificationDto = new NotificationDto();
                    BeanUtils.copyProperties(notification, notificationDto);
                    if (!notification.getRead()) {
                        newNotifications.set(newNotifications.get() + 1);
                        notification.setRead(true);
                        notificationRepository.save(notification);
                    }
                    notificationDtos.add(notificationDto);
                }
            });
            notificationsListDto.setNumberOfNewMessages(newNotifications.get());
            notificationsListDto.setNotifications(notificationDtos);
        }
        return notificationsListDto;
    }

}

package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.NotificationsListDto;
import com.example.smartfarming.repository.NotificationRepository;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.NotificationService;
import com.example.smartfarming.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class NotificationServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    WeatherService weatherService;

    @Test
    void testNotification() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        CurrentWeather currentWeather = weatherService.getWeather();
        notificationService.sendNotifications();
        if(currentWeather.getWeatherDescription().equals("Clear")){
            Assertions.assertEquals(0,notificationRepository.findAll().size());
        } else {
            Assertions.assertTrue(notificationRepository.findAll().size() > 0);
        }
    }

    @Test
    void testGetNotifications() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        NotificationsListDto dto = notificationService.getAllNotifications();
        Assertions.assertNotNull(dto);

    }

}

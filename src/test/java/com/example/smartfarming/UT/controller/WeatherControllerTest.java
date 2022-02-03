package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.WeatherController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
import com.example.smartfarming.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class WeatherControllerTest {

    @Autowired
    WeatherController weatherController;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    ClientService clientService;

    @Test
    void testPublish() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        ResponseEntity<String> response = weatherController.publish("123", new PublishWeather());
        Assertions.assertEquals("Publishing successfully!", response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testFindAll() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        Weather weather = new Weather().setId(UUID.randomUUID().toString()).setSensorId("-1");
        weatherRepository.save(weather);
        List<WeatherDto> list = weatherController.findAllUser();
        Assertions.assertFalse(list.isEmpty());
    }

}

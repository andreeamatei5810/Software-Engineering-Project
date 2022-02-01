package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.WeatherController;
import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
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

    @Test
    void testPublish() throws Exception {
        ResponseEntity<String> response = weatherController.publish("123", new PublishWeather());
        Assertions.assertEquals("Publishing successfully!", response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    /*
    @Test
    void testFindAll() throws Exception {
        Weather weather = new Weather().setId(UUID.randomUUID().toString());
        weatherRepository.save(weather);
        List<WeatherDto> list = weatherController.findAll();
        Assertions.assertFalse(list.isEmpty());
    }
    */
}

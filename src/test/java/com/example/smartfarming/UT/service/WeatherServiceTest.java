package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
import com.example.smartfarming.service.WeatherService;
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
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    void testInitDb() throws IOException {
        weatherService.initializeDatabase();
        Assertions.assertFalse(weatherRepository.findAll().isEmpty());
    }

    @Test
    void testPublish() throws Exception {
        PublishWeather publishSoil = new PublishWeather();
        String message = weatherService.publish(publishSoil);
        Assertions.assertEquals("Publicarea a fost cu succes!", message);
    }

    @Test
    void testFindAll() throws Exception {
        Weather weather = new Weather().setId(UUID.randomUUID().toString());
        weatherRepository.save(weather);
        List<WeatherDto> list = weatherService.findAll();
        Assertions.assertFalse(list.isEmpty());
    }

}

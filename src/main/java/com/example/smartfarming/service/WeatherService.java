package com.example.smartfarming.service;

import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeatherService {
    final WeatherRepository weatherRepository;
    final MessagingService messagingService;

    @PostConstruct
    public void initializeDatabase() throws IOException {
        if (weatherRepository.findAll().isEmpty()) {
            String file = "src/main/resources/data/weather.json";
            String json = new String(Files.readAllBytes(Paths.get(file)));
            ObjectMapper mapper = new ObjectMapper();
            List<Weather> weatherList = mapper.readValue(json, new TypeReference<>() {
            });
            weatherList.forEach(soil -> soil
                    .setId(UUID.randomUUID().toString())
                    .setTimeStamp(LocalDateTime.now())
            );
            weatherRepository.saveAll(weatherList);
        }
    }

    public String publish(PublishWeather weatherDto) throws MqttException {
        Weather weather = new Weather()
                .setId(UUID.randomUUID().toString())
                .setTimeStamp(LocalDateTime.now());
        BeanUtils.copyProperties(weatherDto, weather);
        PublishMessage publishMessage = new PublishMessage()
                .setTopic("/weather")
                .setMessage(weather.toString())
                .setQos(0)
                .setRetained(true);
        messagingService.publish(publishMessage);
        return "Publicarea a fost cu succes!";
    }

    public List<WeatherDto> findAll() {
        List<WeatherDto> weatherDtos = new ArrayList<>();
        weatherRepository.findAll().forEach(soil -> {
            WeatherDto weatherDto = new WeatherDto();
            BeanUtils.copyProperties(soil, weatherDto);
            weatherDtos.add(weatherDto);
        });
        return weatherDtos;
    }


    }


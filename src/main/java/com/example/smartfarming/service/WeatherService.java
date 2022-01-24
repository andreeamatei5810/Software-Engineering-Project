package com.example.smartfarming.service;

import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}&units=metric";

    @Value("${api.weather.key}")
    private String apiKey;
    final WeatherRepository weatherRepository;
    final MessagingService messagingService;
    @Autowired
    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    @PostConstruct
    public void initializeDatabase() throws IOException {
        if (weatherRepository.findAll().isEmpty()) {
            String file = "src/main/resources/data/weather.json";
            String json = new String(Files.readAllBytes(Paths.get(file)));
            List<Weather> weatherList = objectMapper.readValue(json, new TypeReference<>() {
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

    public CurrentWeather getWeather(String country, String city) {
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new CurrentWeather()
                    .setWeatherDescription(root.path("weather").get(0).path("main").asText())
                    .setTemp(root.path("main").path("temp").asLong())
                    .setFeelsLike(root.path("main").path("feels_like").asLong())
                    .setWindSpeed(root.path("wind").path("speed").asLong());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }


    }


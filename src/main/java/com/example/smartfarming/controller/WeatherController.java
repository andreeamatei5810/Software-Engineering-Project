package com.example.smartfarming.controller;

import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<String> publish (@RequestBody PublishWeather weatherDto) throws MqttException {
        return ResponseEntity.ok().body(weatherService.publish(weatherDto));
    }

    @GetMapping
    public List<WeatherDto> findAll(){
        return weatherService.findAll();
    }







}

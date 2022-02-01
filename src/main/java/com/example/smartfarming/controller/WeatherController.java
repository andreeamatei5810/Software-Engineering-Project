package com.example.smartfarming.controller;

import com.example.smartfarming.dto.CurrentWeather;
import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.dto.WeatherDto;
import com.example.smartfarming.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    final WeatherService weatherService;

    @PostMapping("/{sensorId}")
    public ResponseEntity<String> publish (@PathVariable String sensorId,@RequestBody PublishWeather weatherDto) throws MqttException {
        return ResponseEntity.ok().body(weatherService.publish(sensorId,weatherDto));
    }

    @GetMapping("/user")
    public List<WeatherDto> findAllUser(Principal principal){
        return weatherService.findAllUser(principal.getName());
    }

    @GetMapping("/current")
    public CurrentWeather getWeather(Principal principal){
        return weatherService.getWeather(principal.getName());
    }






}

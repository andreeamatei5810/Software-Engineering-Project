package com.example.smartfarming.controller;

import com.example.smartfarming.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
@RequiredArgsConstructor
public class SensorController {

    final SensorService sensorService;

    @PostMapping
    public String add(@RequestParam String clientId) throws MqttException, InterruptedException {
        return sensorService.add(clientId);
    }

}

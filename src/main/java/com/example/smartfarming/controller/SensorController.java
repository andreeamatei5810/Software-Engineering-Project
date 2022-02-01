package com.example.smartfarming.controller;

import com.example.smartfarming.service.SensorService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> add(@RequestParam String clientId){
        return ResponseEntity.ok().body(sensorService.add(clientId));
    }

}

package com.example.smartfarming.service;

import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorService {

    final SensorRepository sensorRepository;
    public String add(String clientId) {
        sensorRepository.save(new Sensor()
                .setId(UUID.randomUUID().toString())
                .setClientId(clientId));

        return "Sensor was added with success!";
    }
}

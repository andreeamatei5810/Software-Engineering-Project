package com.example.smartfarming.service;

import com.example.smartfarming.SmartFarmingApplication;
import com.example.smartfarming.entity.Client;
import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.exception.CustomException;
import com.example.smartfarming.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorService {

    final MessagingService messagingService;

    final SensorRepository sensorRepository;
    public String add(String clientId) throws MqttException, InterruptedException {
        Client currentUser = SmartFarmingApplication.getCurrentUser();
        if(currentUser == null) {
            throw new CustomException("You need to be logged in to do this operation!");
        }
        String sensorId = UUID.randomUUID().toString();
        Sensor sensor = new Sensor()
                .setId(sensorId)
                .setClientId(clientId);
        sensorRepository.save(sensor);
        messagingService.addSoilSubscribe(2000, sensor);
        return sensorId;
    }
}

package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.ClientRepository;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.MessagingService;
import com.example.smartfarming.service.SensorService;
import com.example.smartfarming.service.SoilService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MessagingServiceTest {

    @Autowired
    ClientService clientService;
    @Autowired
    MessagingService messagingService;
    @Autowired
    SensorService sensorService;
    @Autowired
    SoilService soilService;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void testGet() throws MqttException, InterruptedException, IOException {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        String id = sensorService.add("-5");
        soilService.publish(id,new PublishSoil().setMoisture(30));
        List<Soil> response = messagingService.getSoils();
        Assertions.assertNotNull(response);
    }
}
package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.SensorController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.repository.SensorRepository;
import com.example.smartfarming.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class SensorControllerTest {

    @Autowired
    SensorController sensorController;

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    ClientService clientService;

    @Test
    void testAdd() throws Exception {
        String clientId = UUID.randomUUID().toString();
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        String sensorId = sensorController.add(clientId);
        Assertions.assertFalse(sensorId.isEmpty());
    }


}

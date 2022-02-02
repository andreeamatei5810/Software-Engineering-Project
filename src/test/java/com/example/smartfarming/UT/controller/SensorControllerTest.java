package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.SensorController;
import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.repository.SensorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class SensorControllerTest {

    @Autowired
    SensorController sensorController;

    @Autowired
    SensorRepository sensorRepository;

    @Test
    void testAdd() throws Exception {
        String clientId = UUID.randomUUID().toString();

        String sensorId = sensorController.add(clientId);
        Assertions.assertFalse(sensorId.isEmpty());
    }


}

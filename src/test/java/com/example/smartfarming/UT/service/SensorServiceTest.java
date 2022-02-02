package com.example.smartfarming.UT.service;

import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.repository.SensorRepository;
//import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.SensorService;
//import com.example.smartfarming.service.SoilService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")

public class SensorServiceTest {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    void testAdd() throws Exception {
        String clientId = UUID.randomUUID().toString();

        String sensorId = sensorService.add(clientId);
        Assertions.assertFalse(sensorId.isEmpty());
    }


}

package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.exception.CustomException;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.SensorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")

public class SensorServiceTest {

    @Autowired
    private SensorService sensorService;
    @Autowired
    ClientService clientService;


    @Test
    void testAdd() throws Exception {
        String clientId = UUID.randomUUID().toString();
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        String sensorId = sensorService.add(clientId);
        Assertions.assertFalse(sensorId.isEmpty());
    }

    @Test
    void testException() {
        String clientId = UUID.randomUUID().toString();
        Assertions.assertThrows(CustomException.class, () -> sensorService.add(clientId));
    }


}

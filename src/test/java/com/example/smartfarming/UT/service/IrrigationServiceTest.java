package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.IrrigationRepository;
import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.IrrigationService;
import com.example.smartfarming.service.SensorService;
import com.example.smartfarming.service.SoilService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@SpringBootTest
@ActiveProfiles("test")
public class IrrigationServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    IrrigationService irrigationService;

    @Autowired
    IrrigationRepository irrigationRepository;

    @Autowired
    SoilService soilService;

    @Autowired
    SensorService sensorService;

    @Autowired
    SoilRepository soilRepository;

//    @Test
//    void testOn() throws MqttException, IOException, InterruptedException {
//        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
//        String id = sensorService.add("-1");
//        soilService.publish(id,new PublishSoil().setMoisture(0));
//        Soil soil = soilRepository.findFirstBySensorId(id);
//        irrigationService.powerOn();
//        Assertions.assertTrue(irrigationRepository.findBySoil(soil).get().getPower());
//    }

    @Test
    void testOff() throws MqttException, IOException, InterruptedException {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        String id = sensorService.add("-2");
        soilService.publish(id,new PublishSoil().setMoisture(30));
        Soil soil = soilRepository.findFirstBySensorId(id);
        irrigationService.powerOff();
        Assertions.assertFalse(irrigationRepository.findBySoil(soil).get().getPower());
    }

}
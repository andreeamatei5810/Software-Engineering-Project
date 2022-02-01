package com.example.smartfarming.service;

import com.example.smartfarming.dto.*;
import com.example.smartfarming.entity.IrrigationSystem;
import com.example.smartfarming.entity.Notification;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.IrrigationRepository;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IrrigationService {

    final IrrigationRepository irrigationRepository;
    final MessagingService messagingService;

    @PostConstruct
    public void subscribeToSoilTopic() throws MqttException, InterruptedException {
        messagingService.subscribeSoil(2000);
    }

    @Scheduled(fixedRate = 60*5000, initialDelay = 60*1000)
    public void powerOn() throws MqttException{
        List<Soil> soilList = messagingService.getSoils();
        System.out.println("powerOn");
        for (Soil soil : soilList) {
            if (soil.getMoisture() != null && soil.getMoisture() < 20) {
                Optional<IrrigationSystem> irrigationSystemOptional = irrigationRepository.findBySoil(soil);
                IrrigationSystem irrigationSystem = new IrrigationSystem();
                if(irrigationSystemOptional.isPresent()) {
                    irrigationSystem = irrigationSystemOptional.get().setPower(true)
                            .setTimeStamp(LocalDateTime.now()).setWaterAmount(20 - soil.getMoisture());
                }
                else{
                    irrigationSystem.setId(UUID.randomUUID().toString()).setPower(true)
                            .setTimeStamp(LocalDateTime.now()).setWaterAmount(20 - soil.getMoisture()).setSoil(soil);
                }
                irrigationRepository.save(irrigationSystem);
                PublishMessage publishMessage = new PublishMessage()
                        .setTopic("/irrigation")
                        .setMessage(irrigationSystem.toString())
                        .setQos(0)
                        .setRetained(true);
                messagingService.publish(publishMessage);
            }
        }
    }

    @Scheduled(fixedRate = 60*4000, initialDelay = 60*50)
    public void powerOff() throws MqttException {
        List<Soil> soilList = messagingService.getSoils();
        System.out.println("powerOff");
        for (Soil soil : soilList) {
            if (soil.getMoisture() != null && soil.getMoisture() > 20) {
                Optional<IrrigationSystem> irrigationSystemOptional = irrigationRepository.findBySoil(soil);
                IrrigationSystem irrigationSystem = new IrrigationSystem();
                if(irrigationSystemOptional.isPresent()) {
                    irrigationSystem = irrigationSystemOptional.get().setPower(false)
                            .setTimeStamp(LocalDateTime.now()).setWaterAmount(0);
                }
                else{
                    irrigationSystem.setId(UUID.randomUUID().toString()).setPower(false)
                            .setTimeStamp(LocalDateTime.now()).setSoil(soil);
                }
                irrigationRepository.save(irrigationSystem);
                PublishMessage publishMessage = new PublishMessage()
                        .setTopic("/irrigation")
                        .setMessage(irrigationSystem.toString())
                        .setQos(0)
                        .setRetained(true);
                messagingService.publish(publishMessage);
            }
        }
    }




}

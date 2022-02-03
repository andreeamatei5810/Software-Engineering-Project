package com.example.smartfarming.service;

import com.example.smartfarming.SmartFarmingApplication;
import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.entity.Client;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.exception.CustomException;
import com.example.smartfarming.repository.SoilRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SoilService {

	final SoilRepository soilRepository;
	final MessagingService messagingService;
	final IrrigationService irrigationService;

	@PostConstruct
	public void initializeDatabase() throws IOException {
		if(soilRepository.findAll().isEmpty()) {
			String file = "src/main/resources/data/soil.json";
			String json = new String(Files.readAllBytes(Paths.get(file)));
			ObjectMapper mapper = new ObjectMapper();
			List<Soil> soilList = mapper.readValue(json, new TypeReference<>() {
			});
			soilList.forEach(soil -> soil
						.setId(UUID.randomUUID().toString())
						.setTimeStamp(LocalDateTime.now())
					.setSensorId("1")
			);
			soilRepository.saveAll(soilList);
		}
	}

	public String publish(String sensorId, PublishSoil soilDto) throws MqttException, IOException {
		Client currentUser = SmartFarmingApplication.getCurrentUser();
		if(currentUser == null) {
			throw new CustomException("You need to be logged in to do this operation!");
		}
		Soil soil = new Soil()
				.setId(UUID.randomUUID().toString())
				.setTimeStamp(LocalDateTime.now())
				.setSensorId(sensorId);
		BeanUtils.copyProperties(soilDto, soil);
		ObjectMapper mapper = new ObjectMapper();
		String jsonSoil = mapper.writeValueAsString(soil);
		PublishMessage publishMessage = new PublishMessage()
				.setTopic(sensorId + "/soil")
				.setMessage(jsonSoil)
				.setQos(0)
				.setRetained(true);
		messagingService.publish(publishMessage);
		soilRepository.save(soil);
		return "Publishing successfully!";
	}

	public List<SoilDto> findAllUser(){
		Client currentUser = SmartFarmingApplication.getCurrentUser();
		if(currentUser == null) {
			throw new CustomException("You need to be logged in to do this operation!");
		}
		List<SoilDto> soilDtos = new ArrayList<>();
		soilRepository.findAllByEmail(currentUser.getEmail()).forEach(soil -> {
			SoilDto soilDto = new SoilDto(soil);
			soilDtos.add(soilDto);
		});
		return soilDtos;
	}
}

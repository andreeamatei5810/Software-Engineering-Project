package com.example.smartfarming.service;

import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.entity.Soil;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SoilService {

	final SoilRepository soilRepository;
	final MessagingService messagingService;

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
			);
			soilRepository.saveAll(soilList);
		}
	}


	public String publish(PublishSoil soilDto) throws MqttException {
		Soil soil = new Soil()
				.setId(UUID.randomUUID().toString())
				.setTimeStamp(LocalDateTime.now());
		BeanUtils.copyProperties(soilDto, soil);
		PublishMessage publishMessage = new PublishMessage()
				.setTopic("/soil")
				.setMessage(soil.toString())
				.setQos(0)
				.setRetained(true);
		messagingService.publish(publishMessage);
		return "Publicarea a fost cu succes!";
	}
}

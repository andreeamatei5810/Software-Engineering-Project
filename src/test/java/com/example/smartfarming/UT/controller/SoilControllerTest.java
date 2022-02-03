package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.SoilController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.ClientService;
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
public class SoilControllerTest {

	@Autowired
	SoilController soilController;

	@Autowired
	SoilRepository soilRepository;

	@Autowired
	ClientService clientService;

	@Test
	void testPublish() throws Exception {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		ResponseEntity<String> response = soilController.publish("123",new PublishSoil());
		Assertions.assertEquals("Publishing successfully!", response.getBody());
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	void testFindAll() throws Exception {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		Soil soil = new Soil().setId(UUID.randomUUID().toString()).setSensorId("-1");
		soilRepository.save(soil);
		List<SoilDto> list = soilController.findAllUser();
		Assertions.assertFalse(list.isEmpty());
	}
}

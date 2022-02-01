package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.SoilController;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SoilRepository;
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

	@Test
	void testPublish() throws Exception {
		ResponseEntity<String> response = soilController.publish("123",new PublishSoil());
		Assertions.assertEquals("Publishing successfully!", response.getBody());
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}

	/*
	@Test
	void testFindAll() throws Exception {
		Soil soil = new Soil().setId(UUID.randomUUID().toString());
		soilRepository.save(soil);
		List<SoilDto> list = soilController.findAll();
		Assertions.assertFalse(list.isEmpty());
	}
	*/
}

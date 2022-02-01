package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.SoilService;
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
class SoilServiceTest {

	@Autowired
	private SoilService soilService;
	@Autowired
	private SoilRepository soilRepository;

	@Test
	void testInitDb() throws IOException {
		soilService.initializeDatabase();
		Assertions.assertFalse(soilRepository.findAll().isEmpty());
	}

	@Test
	void testPublish() throws Exception {
		PublishSoil publishSoil = new PublishSoil();
		String message = soilService.publish("123", publishSoil);
		Assertions.assertEquals("Publishing successfully!", message);
	}

	@Test
	void testFindAll() throws Exception {
		Soil soil = new Soil().setId(UUID.randomUUID().toString());
		soilRepository.save(soil);
		List<SoilDto> list = soilService.findAll();
		Assertions.assertFalse(list.isEmpty());
	}

}

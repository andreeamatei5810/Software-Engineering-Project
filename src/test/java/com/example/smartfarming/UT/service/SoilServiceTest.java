package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.ClientService;
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
	@Autowired
	ClientService clientService;

	@Test
	void testInitDb() throws IOException {
		soilService.initializeDatabase();
		Assertions.assertFalse(soilRepository.findAll().isEmpty());
	}

	@Test
	void testPublish() throws Exception {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		PublishSoil publishSoil = new PublishSoil();
		String message = soilService.publish("123", publishSoil);
		Assertions.assertEquals("Publishing successfully!", message);
	}


	@Test
	void testFindAll() {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		Soil soil = new Soil().setId(UUID.randomUUID().toString()).setSensorId("-1");
		soilRepository.save(soil);
		List<SoilDto> list = soilService.findAllUser();
		Assertions.assertFalse(list.isEmpty());
	}

}

package com.example.smartfarming.IT;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SoilRepository;
import com.example.smartfarming.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class SoilIntegrationTest {

	@Autowired
	SoilRepository soilRepository;

	@Autowired
	ClientService clientService;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void testPublish() throws Exception {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		PublishSoil publishSoil = new PublishSoil();
        String sensorId = UUID.randomUUID().toString();
		mockMvc.perform(post("/soil/" + sensorId)
						.content(objectMapper.writeValueAsString(publishSoil))
						.param("sensorId", sensorId)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void testFindAll() throws Exception {
		clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
		soilRepository.save(new Soil().setId(UUID.randomUUID().toString()).setSensorId("-1"));
		mockMvc.perform(get("/soil/user"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(not(empty()))));

	}

}

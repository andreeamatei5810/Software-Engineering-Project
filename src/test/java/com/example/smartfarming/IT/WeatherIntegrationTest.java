package com.example.smartfarming.IT;

import com.example.smartfarming.dto.PublishWeather;
import com.example.smartfarming.entity.Weather;
import com.example.smartfarming.repository.WeatherRepository;
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
class WeatherIntegrationTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testPublish() throws Exception {
        PublishWeather publishWeather = new PublishWeather();
        String sensorId = UUID.randomUUID().toString();
        mockMvc.perform(post("/weather/" + sensorId)
                        .content(objectMapper.writeValueAsString(publishWeather))
                        .param("sensorId", sensorId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAll() throws Exception {
        weatherRepository.save(new Weather().setId(UUID.randomUUID().toString()));

        mockMvc.perform(get("/weather"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));

    }

}

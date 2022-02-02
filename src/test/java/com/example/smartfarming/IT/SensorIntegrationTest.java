package com.example.smartfarming.IT;

import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.repository.SensorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
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
class SensorIntegrationTest {

    @Autowired
    SensorRepository sensorRepository;

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
    void testAdd() throws Exception {
        sensorRepository.save(new Sensor().setId(UUID.randomUUID().toString()));

        mockMvc.perform(get("/sensor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));

    }

}

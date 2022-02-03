package com.example.smartfarming.IT;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.repository.SensorRepository;
import com.example.smartfarming.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class SensorIntegrationTest {

    @Autowired
    SensorRepository sensorRepository;

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
    void testAdd() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        mockMvc.perform(post("/sensor")
                        .param("clientId", "-1"))
                .andExpect(status().isOk());
    }

}

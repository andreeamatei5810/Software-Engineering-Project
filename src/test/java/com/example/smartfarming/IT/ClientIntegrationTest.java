package com.example.smartfarming.IT;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.ClientRegister;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class ClientIntegrationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClientService clientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void testRegister() throws Exception {
        ClientRegister clientRegister = new ClientRegister()
                .setEmail("mimi@example.com")
                .setPassword("parola").setPasswordCheck("parola").setCity("Bucharest").setCountry("Romania");
        mockMvc.perform(post("/client/register")
                        .content(objectMapper.writeValueAsString(clientRegister))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin() throws Exception {
        ClientLogin clientLogin = new ClientLogin()
                .setEmail("test")
                .setPassword("test");
        mockMvc.perform(post("/client/login")
                        .content(objectMapper.writeValueAsString(clientLogin))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
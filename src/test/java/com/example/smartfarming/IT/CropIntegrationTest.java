package com.example.smartfarming.IT;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
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
class CropIntegrationTest {

    @Autowired
    CropRepository cropRepository;

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
    void testSaveCrop() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        CropDto cropDto = new CropDto();
        String sensorId = UUID.randomUUID().toString();
        mockMvc.perform(post("/crop/addCrop/" + sensorId)
                        .content(objectMapper.writeValueAsString(cropDto))
                        .param("sensorId", sensorId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk());
    }


    @Test
    void testShowCrops() throws Exception {
        cropRepository.save(new Crop().setId(UUID.randomUUID().toString()).setSensorId("-1"));
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        mockMvc.perform(get("/crop/showCropsUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))));

    }

}

package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.CropController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
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
public class CropControllerTest {

    @Autowired
    CropController cropController;

    @Autowired
    CropRepository cropRepository;

    @Autowired
    ClientService clientService;

    @Test
    void testSaveCrops() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        ResponseEntity<String> response = cropController.saveCrop("123", new CropDto());
        Assertions.assertEquals("Publishing successfully!", response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testShowCrops() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        Crop crop = new Crop().setId(UUID.randomUUID().toString()).setSensorId("-1");
        cropRepository.save(crop);
        List<CropDto> list = cropController.showCropsUser();
        Assertions.assertFalse(list.isEmpty());
    }

}

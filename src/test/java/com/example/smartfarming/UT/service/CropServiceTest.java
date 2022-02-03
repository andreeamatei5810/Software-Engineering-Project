package com.example.smartfarming.UT.service;

//import com.example.smartfarming.dto.PublishSoil;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.CropService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class CropServiceTest {

    @Autowired
    private CropService cropService;
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    ClientService clientService;


    @Test
    void testSaveCrops() throws Exception {
        cropService.saveCrops();
        Assertions.assertFalse(cropRepository.findAll().isEmpty());
    }

    @Test
    void testSaveCrop() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        CropDto cropDto = new CropDto();
        String message = cropService.saveCrop("123", cropDto);
        Assertions.assertEquals("Publishing successfully!", message);
    }

    @Test
    void testShowCrops() throws Exception {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        Crop crop = new Crop().setId(UUID.randomUUID().toString()).setSensorId("-1");
        cropRepository.save(crop);
        List<CropDto> list = cropService.showCropsUser();
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void testReadCropsFromJson() {
        List<CropDto> list = cropService.readCropsFromJson();
        Assertions.assertFalse(list.isEmpty());
    }

}

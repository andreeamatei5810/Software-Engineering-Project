package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.CropController;
import com.example.smartfarming.dto.CropDto;
// import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
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

    @Test
    void testSaveCrops() throws Exception {
        ResponseEntity<String> response = cropController.saveCrops(new CropDto());
        //Assertions.assertEquals("The data has been retrieved.", response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testShowCrops() throws Exception {
        Crop crop = new Crop().setId(UUID.randomUUID().toString());
        cropRepository.save(crop);
        List<CropDto> list = cropController.showCrops();
        Assertions.assertFalse(list.isEmpty());
    }

}

package com.example.smartfarming.UT.service;

//import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
import com.example.smartfarming.service.CropService;
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
class CropServiceTest {

    @Autowired
    private CropService cropService;
    @Autowired
    private CropRepository cropRepository;


    @Test
    void testSaveCrops() throws Exception {

        String message = cropService.saveCrops();
        Assertions.assertEquals("The data has been retrieved.", message);
    }


    @Test
    void testSaveCrop() throws Exception {
        CropDto cropDto = new CropDto();
        String message = cropService.saveCrop(cropDto);
        Assertions.assertEquals("Crop is saved.", message);
    }

    @Test
    void testShowCrops() throws Exception {
        Crop crop = new Crop().setId(UUID.randomUUID().toString());
        cropRepository.save(crop);
        List<CropDto> list = cropService.showCrops();
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void testReadCropsFromJson() throws Exception {
        Crop crop = new Crop().setId(UUID.randomUUID().toString());
        cropRepository.save(crop);
        List<CropDto> list = cropService.readCropsFromJson();
        Assertions.assertFalse(list.isEmpty());
    }

}

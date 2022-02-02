package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.IrrigationSystemDto;
import com.example.smartfarming.entity.IrrigationSystem;
import com.example.smartfarming.repository.IrrigationRepository;
import com.example.smartfarming.service.IrrigationService;
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
class IrrigationServiceTest {

    @Autowired
    private IrrigationService irrigationService;
    @Autowired
    private IrrigationRepository irrigationRepository;




}

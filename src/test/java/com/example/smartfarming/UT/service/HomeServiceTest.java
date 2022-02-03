package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.HomeDto;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.HomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class HomeServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    HomeService homeService;

    @Test
    void testHome() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        HomeDto response = homeService.getReport(LocalDate.now().minusMonths(5));
        Assertions.assertNotNull(response);
    }
}

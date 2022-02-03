package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.HomeController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.HomeDto;
import com.example.smartfarming.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    ClientService clientService;

    @Autowired
    HomeController homeController;

    @Test
    void testRegister() {
        clientService.login(new ClientLogin().setEmail("test").setPassword("parola"));
        ResponseEntity<HomeDto> response = homeController.home(LocalDate.now().minusMonths(5));
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }
}

package com.example.smartfarming.UT.controller;

import com.example.smartfarming.controller.ClientController;
import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClientControllerTest {

    @Autowired
    ClientController clientController;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void testRegister() {
        ResponseEntity<String> response = clientController.register(new ClientRegister()
                .setEmail("mimi@example.com")
                .setPassword("parola").setPasswordCheck("parola").setCity("Bucharest").setCountry("Romania"));
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testRegisterPasswordsNotMatch() {
        ResponseEntity<String> response = clientController.register(new ClientRegister()
                .setEmail("mimi@example.com")
                .setPassword("parola").setPasswordCheck("parolaa"));
        Assertions.assertEquals("Passwords do not match!", response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testLogin() {
        ResponseEntity<String> response = clientController.login(new ClientLogin()
                .setEmail("test")
                .setPassword("parola"));
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("User has logged in!", response.getBody());
    }

    @Test
    void testFailed() {
        ResponseEntity<String> response = clientController.login(new ClientLogin()
                .setEmail("test")
                .setPassword("testtttt"));
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Password or email is wrong!", response.getBody());
    }

}
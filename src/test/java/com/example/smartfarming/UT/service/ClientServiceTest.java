package com.example.smartfarming.UT.service;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.repository.ClientRepository;
import com.example.smartfarming.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClientServiceTest {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void testRegister() {
        String response = clientService.register(new ClientRegister()
                .setEmail("mimi@example.com")
                .setPassword("parola").setPasswordCheck("parola"));
        Assertions.assertNotNull(response);
    }

    @Test
    void testRegisterPasswordsNotMatch() {
        String response = clientService.register(new ClientRegister()
                .setEmail("mimicutica@example.com")
                .setPassword("parola").setPasswordCheck("parolahhtht").setCity("Bucharest").setCountry("Romania"));
        Assertions.assertEquals("Passwords do not match!", response);
    }

    @Test
    void testLogin() {
        String response = clientService.login(new ClientLogin()
                .setEmail("test")
                .setPassword("parola"));
        Assertions.assertEquals("User has logged in!", response);
    }

    @Test
    void testFailed() {
        String response = clientService.login(new ClientLogin()
                .setEmail("test")
                .setPassword("testtttt"));
        Assertions.assertEquals("Password or email is wrong!", response);
    }

}
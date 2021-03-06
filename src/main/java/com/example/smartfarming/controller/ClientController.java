package com.example.smartfarming.controller;

import com.example.smartfarming.dto.ClientLogin;
import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

	final ClientService clientService;

	@PostMapping("/register")
	public ResponseEntity<String> register (@RequestBody ClientRegister clientRegister){
		return ResponseEntity.ok().body(clientService.register(clientRegister));
	}


	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody ClientLogin clientLogin) {
		return ResponseEntity.ok().body(clientService.login(clientLogin));
	}
}

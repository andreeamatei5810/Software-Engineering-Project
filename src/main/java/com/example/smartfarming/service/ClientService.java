package com.example.smartfarming.service;

import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.entity.Client;
import com.example.smartfarming.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

	final ClientRepository clientRepository;

	public String register(ClientRegister clientRegister) {
		Optional<Client> clientOptional = clientRepository.findByEmail(clientRegister.getEmail());
		if (clientOptional.isPresent()){
			return "There is an account with this email";
		} else if(clientRegister.getPassword().equals(clientRegister.getPasswordCheck())) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Client client = new Client();
			BeanUtils.copyProperties(clientRegister,client);
			client.setPassword(encoder.encode(client.getPassword()));
			String id = UUID.randomUUID().toString();
			client.setId(id);
			clientRepository.save(client);
			return id;
		} else {
			return "Passwords do not match!";
		}
	}
}

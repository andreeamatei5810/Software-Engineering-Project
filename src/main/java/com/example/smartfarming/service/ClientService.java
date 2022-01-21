package com.example.smartfarming.service;

import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.entity.Client;
import com.example.smartfarming.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
			return "Exista un cont cu mail-ul asta";
		} else if(clientRegister.getPassword().equals(clientRegister.getPasswordCheck())) {
			Client client = new Client();
			BeanUtils.copyProperties(clientRegister,client);
			client.setId(UUID.randomUUID().toString());
			clientRepository.save(client);
			return "V-ati inregistrat cu succes";
		} else {
			return "Parolele nu se potrivesc!";
		}
	}
}

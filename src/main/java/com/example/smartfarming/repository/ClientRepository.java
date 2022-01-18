package com.example.smartfarming.repository;

import com.example.smartfarming.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,String> {

	Optional<Client> findByEmail(String email);

}

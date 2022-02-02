package com.example.smartfarming;

import com.example.smartfarming.entity.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartFarmingApplication {

	private static Client currentUser = null;


	public static void main(String[] args) {
		SpringApplication.run(SmartFarmingApplication.class, args);
	}


	public static void setCurrentUser(Client user) {
		currentUser = user;
	}

	public static Client getCurrentUser() {
		return currentUser;
	}

}

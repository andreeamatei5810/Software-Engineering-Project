package com.example.smartfarming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartFarmingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFarmingApplication.class, args);
	}

}

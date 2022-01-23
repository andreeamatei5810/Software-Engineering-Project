package com.example.smartfarming.controller;

import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.service.SoilService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soil")
@RequiredArgsConstructor
public class SoilController {

	final SoilService soilService;

	@PostMapping
	public ResponseEntity<String> publish (@RequestBody PublishSoil soilDto) throws MqttException {
		return ResponseEntity.ok().body(soilService.publish(soilDto));
	}

	@GetMapping
	public List<SoilDto> findAll(){
		return soilService.findAll();
	}

}

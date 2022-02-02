package com.example.smartfarming.controller;

import com.example.smartfarming.dto.PublishSoil;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.service.SoilService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/soil")
@RequiredArgsConstructor
public class SoilController {

	final SoilService soilService;

	@PostMapping("/{sensorId}")
	public ResponseEntity<String> publish (@PathVariable String sensorId, @RequestBody PublishSoil soilDto) throws MqttException, IOException {
		return ResponseEntity.ok().body(soilService.publish(sensorId, soilDto));
	}

	@GetMapping("/user")
	public List<SoilDto> findAllUser(){
		return soilService.findAllUser();
	}

}

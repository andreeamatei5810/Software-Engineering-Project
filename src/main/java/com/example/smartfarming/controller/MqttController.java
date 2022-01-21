package com.example.smartfarming.controller;

import com.example.smartfarming.dto.MqttSubscribeModel;
import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/mqtt")
@RequiredArgsConstructor
public class MqttController {

	final MessagingService messagingService;

	@PostMapping("/publish")
	public void publishMessage(@RequestBody @Valid PublishMessage publishMessage) throws MqttException {

		messagingService.publish(publishMessage);
	}

	@GetMapping("/subscribe")
	public MqttSubscribeModel subscribeChannel(@RequestParam(value = "topic") String topic,
											   @RequestParam(value = "wait_millis") Integer waitMillis)
			throws InterruptedException, MqttException {

		return messagingService.subscribeList(topic, waitMillis);
	}

	@GetMapping("/all")
	public List<MqttSubscribeModel> showAllReceived() {
		return messagingService.showAllReceived();
	}
}

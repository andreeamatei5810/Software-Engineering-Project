package com.example.smartfarming.service;

import com.example.smartfarming.dto.MqttSubscribeModel;
import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.entity.Soil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MessagingService {

	@Autowired
	private IMqttClient mqttClient;
	List<MqttSubscribeModel> messages = new ArrayList<>();
	List<Soil> soilMessages = new ArrayList<>();

	public void subscribeSoil(final Integer waitMillis) throws MqttException, InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ObjectMapper mapper = new ObjectMapper();
		mqttClient.subscribe("/soil", (s, mqttMessage) -> {
			soilMessages.add(mapper.readValue(mqttMessage.getPayload(), Soil.class));
			countDownLatch.countDown();
		});
		countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);
	}


	public void publish(final String topic, final String payload, int qos, boolean retained)
			throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(payload.getBytes());
		mqttMessage.setQos(qos);
		mqttMessage.setRetained(retained);

		mqttClient.publish(topic, mqttMessage);
	}

	public void publish(PublishMessage publishMessage)
			throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(publishMessage.getMessage().getBytes());
		mqttMessage.setQos(publishMessage.getQos());
		mqttMessage.setRetained(publishMessage.getRetained());

		mqttClient.publish(publishMessage.getTopic(), mqttMessage);
	}

	public void subscribe(final String topic) throws MqttException {
		System.out.println("Messages received:");

		mqttClient.subscribeWithResponse(topic, (tpic, msg) -> System.out.println(msg.getId() + " -> " + new String(msg.getPayload())));
	}

	public MqttSubscribeModel subscribeList(final String topic, final Integer waitMillis) throws MqttException, InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		MqttSubscribeModel mqttSubscribeModelOut;
		mqttClient.subscribe(topic, (s, mqttMessage) -> {
			MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
			mqttSubscribeModel.setId(mqttMessage.getId());
			mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
			mqttSubscribeModel.setQos(mqttMessage.getQos());
			messages.add(mqttSubscribeModel);
			countDownLatch.countDown();
		});

		countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);

		return null;
	}

	public List<MqttSubscribeModel> showAllReceived() {
		return messages;
	}

	public List<Soil> getSoils() {
		return soilMessages;
	}



}
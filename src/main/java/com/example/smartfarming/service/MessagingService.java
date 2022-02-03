package com.example.smartfarming.service;

import com.example.smartfarming.dto.PublishMessage;
import com.example.smartfarming.entity.Sensor;
import com.example.smartfarming.entity.Soil;
import com.example.smartfarming.repository.SensorRepository;
import com.example.smartfarming.repository.SoilRepository;
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
	List<Soil> soilMessages = new ArrayList<>();
	final SoilRepository soilRepository;
	final SensorRepository sensorRepository;

	public void subscribeSoil(final Integer waitMillis) throws MqttException, InterruptedException {
		List<Sensor> sensorList = sensorRepository.findAll();
		for(Sensor sensor:sensorList){
			addSoilSubscribe(waitMillis, sensor);
		}
	}

	public void publish(final String topic, final String payload, int qos, boolean retained)
			throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(payload.getBytes());
		mqttMessage.setQos(qos);
		mqttMessage.setRetained(retained);
		mqttMessage.setId((int) (System.currentTimeMillis() & 0xfffffff));
		mqttClient.publish(topic, mqttMessage);
	}

	public void publish(PublishMessage publishMessage)
			throws MqttException {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(publishMessage.getMessage().getBytes());
		mqttMessage.setQos(publishMessage.getQos());
		mqttMessage.setRetained(publishMessage.getRetained());
		mqttMessage.setId((int) (System.currentTimeMillis() & 0xfffffff));
		mqttClient.publish(publishMessage.getTopic(), mqttMessage);
	}


	public List<Soil> getSoils() {
		return soilMessages;
	}

	public void addSoilSubscribe(final Integer waitMillis, Sensor newSensor) throws MqttException, InterruptedException{
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ObjectMapper mapper = new ObjectMapper();
		mqttClient.subscribe((newSensor.getId() + "/soil"), (s, mqttMessage) -> {
			Soil soil = mapper.readValue(mqttMessage.getPayload(), Soil.class);
			soilMessages.add(soil);
			soilRepository.save(soil);
			countDownLatch.countDown();
		});
		countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);
	}



}
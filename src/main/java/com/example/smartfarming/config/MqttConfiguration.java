package com.example.smartfarming.config;


import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions() {
        return new MqttConnectOptions();
    }

    @Bean
    public IMqttClient mqttClient(
                                  @Value("${mqtt.hostname}") String hostname, @Value("${mqtt.port}") int port) throws MqttException {

        IMqttClient mqttClient = new MqttClient("tcp://" + hostname + ":" + port, MqttAsyncClient.generateClientId());

        mqttClient.connect(mqttConnectOptions());

        return mqttClient;
    }

}
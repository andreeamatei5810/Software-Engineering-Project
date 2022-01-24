package com.example.smartfarming.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishWeather {

    String infoMessage;
    Integer temperature;
    Integer humidity;
    Integer wind;
}

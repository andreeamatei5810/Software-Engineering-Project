package com.example.smartfarming.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherDto {

    String id;
    LocalDateTime timeStamp;
    String infoMessage;
    Integer temperature;
    Integer humidity;
    Integer wind;
}

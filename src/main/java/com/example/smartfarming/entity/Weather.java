package com.example.smartfarming.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Weather {
    @Id
    String id;
    LocalDateTime timeStamp;
    String infoMessage;
    Integer temperature;
    Integer humidity;
    Integer wind;

    @Override
    public String toString() {
        return
                "id='" + id + '\'' +
                        ", timeStamp=" + timeStamp +
                        ", String=" + infoMessage +
                        ", temperature=" + temperature + "°C"+
                        ", humidity=" + humidity +"%"+
                        ", wind=" + wind + "km/h";
    }
}

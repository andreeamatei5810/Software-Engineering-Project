package com.example.smartfarming.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Crop {

    @Id
    String id;
    String name;
    LocalDateTime timeStamp;
    String sensorId;
    Long height ;
    String leafColor;
    Long leafTemperature;


    @Override
    public String toString() {
        return "Crop{" +
                " name='" + name + '\'' +
                ", height=" + height +
                ", leafColor='" + leafColor + '\'' +
                ", leafTemperature=" + leafTemperature +
                '}';
    }
}

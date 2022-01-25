package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class IrrigationSystem {

    @Id
    String id;
    Integer waterAmount;
    LocalDateTime timeStamp;
    Boolean power;
    @ManyToOne
    Soil soil;

    @Override
    public String toString() {
        return "IrrigationSystem{" +
                "waterAmount=" + waterAmount +
                ", timeStamp=" + timeStamp +
                ", power=" + power +
                ", soil=" + soil +
                '}';
    }
}

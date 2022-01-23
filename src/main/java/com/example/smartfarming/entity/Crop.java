package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Crop {

    @Id
    String id;
    @Column(unique = true)
    String name;
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
package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class CropDto {

    String name;
    Long height ;
    String leafColor;
    Long leafTemperature;

    public CropDto(String name, Long height, String leafColor, Long leafTemperature) {
        this.name = name;
        this.height = height;
        this.leafColor = leafColor;
        this.leafTemperature = leafTemperature;
    }

    public CropDto() {

    }
}

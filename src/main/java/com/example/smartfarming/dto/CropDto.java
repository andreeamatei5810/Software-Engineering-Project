package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class CropDto {

    String name;
    Float height ;
    String leafColor;
    Float leafTemperature;
}

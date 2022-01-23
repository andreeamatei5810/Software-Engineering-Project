package com.example.smartfarming.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropDto {

    String name;
    Long height ;
    String leafColor;
    Long leafTemperature;

}

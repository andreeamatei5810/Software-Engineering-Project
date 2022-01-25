package com.example.smartfarming.dto;


import com.example.smartfarming.entity.Soil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationSystemDto {

    Integer waterAmount;
    Boolean power;
    SoilDto soilDto;

}

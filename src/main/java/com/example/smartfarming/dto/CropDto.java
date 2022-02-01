package com.example.smartfarming.dto;

import com.example.smartfarming.entity.Crop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropDto {

    String name;
    Long height;
    String leafColor;
    Long leafTemperature;
    LocalDateTime timeStamp;
    String sensorId;

    public CropDto(Crop crop) {
        BeanUtils.copyProperties(crop, this);
    }

}

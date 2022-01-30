package com.example.smartfarming.dto;

import com.example.smartfarming.entity.Crop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropDto {

    String name;
    Long height;
    String leafColor;
    Long leafTemperature;

    public CropDto(Crop crop) {
        BeanUtils.copyProperties(crop, this);
    }

}

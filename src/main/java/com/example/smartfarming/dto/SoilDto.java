package com.example.smartfarming.dto;

import com.example.smartfarming.entity.Soil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SoilDto {

	String id;
	LocalDateTime timeStamp;
	String sensorId;
	Integer moisture;
	Long pH;
	Integer residualNitrates;
	Long nutrientLevels;
	String texture;
	Long density;
	Long permeability;

	public SoilDto(Soil soil){
		BeanUtils.copyProperties(soil,this);
	}

}

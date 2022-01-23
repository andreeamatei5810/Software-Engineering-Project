package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SoilDto {

	String id;
	LocalDateTime timeStamp;
	Integer moisture;
	Long pH;
	Integer residualNitrates;
	Long nutrientLevels;
	String texture;
	Long density;
	Long permeability;

}

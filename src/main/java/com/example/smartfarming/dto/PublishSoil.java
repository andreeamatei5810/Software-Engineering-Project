package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishSoil {

	Integer moisture;
	Long pH;
	Integer residualNitrates;
	Long nutrientLevels;
	String texture;
	Long density;
	Long permeability;

}

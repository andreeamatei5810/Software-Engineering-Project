package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Soil {

	@Id
	String id;
	LocalDateTime timeStamp;
	Integer moisture;
	Long pH;
	Integer residualNitrates;
	Long nutrientLevels;
	String texture;
	Long density;
	Long permeability;

	@Override
	public String toString() {
		return
				"id='" + id + '\'' +
				", timeStamp=" + timeStamp +
				", moisture=" + moisture +
				", pH=" + pH +
				", residualNitrates=" + residualNitrates +
				", nutrientLevels=" + nutrientLevels +
				", texture='" + texture + '\'' +
				", density=" + density +
				", permeability=" + permeability;
	}
}

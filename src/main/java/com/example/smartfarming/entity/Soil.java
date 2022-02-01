package com.example.smartfarming.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	LocalDateTime timeStamp;
	String sensorId;
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

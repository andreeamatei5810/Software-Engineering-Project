package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeather {

	String weatherDescription;
	Long temp;
	Long feelsLike;
	Long windSpeed;

}

package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, String> {
}

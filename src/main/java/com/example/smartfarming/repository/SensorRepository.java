package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, String> {

}
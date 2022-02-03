package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SoilRepository extends JpaRepository<Soil, String> {

    @Query(value="select s from Soil s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email and s.timeStamp > :localDateTime")
    List<Soil> findAllByTimeStampAndEmail(LocalDateTime localDateTime, String email);

    @Query(value="select s from Soil s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email")
    List<Soil> findAllByEmail(String email);

    Soil findFirstBySensorId(String sensorId);

}

package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, String> {

    @Query(value="select s from Weather s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email and s.timeStamp < :localDateTime")
    List<Weather> findAllByTimeStampAndEmail(LocalDateTime localDateTime, String email);

    @Query(value="select s from Weather s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email")
    List<Weather> findAllByEmail(String email);

}

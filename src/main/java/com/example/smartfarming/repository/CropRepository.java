package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop,String> {
    Optional<Crop> findByName(String name);

    @Query(value="select s from Crop s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email and s.timeStamp > :localDateTime")
    List<Crop> findAllByTimeStampAndEmail(LocalDateTime localDateTime, String email);

    @Query(value="select s from Crop s, Sensor se, Client c where s.sensorId=se.id and se.clientId = c.id and c.email = :email")
    List<Crop> findAllByEmail(String email);


}

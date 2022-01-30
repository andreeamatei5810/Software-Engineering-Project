package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SoilRepository extends JpaRepository<Soil, String> {

    List<Soil> findAllByTimeStampAfter(LocalDateTime localDateTime);

}

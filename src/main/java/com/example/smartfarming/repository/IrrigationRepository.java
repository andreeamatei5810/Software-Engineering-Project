package com.example.smartfarming.repository;

import com.example.smartfarming.entity.IrrigationSystem;
import com.example.smartfarming.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IrrigationRepository extends JpaRepository<IrrigationSystem,String> {

    Optional<IrrigationSystem> findBySoil(Soil soil);

    Optional<IrrigationSystem> findBySoilOrderByTimeStampDesc(Soil soil);


}

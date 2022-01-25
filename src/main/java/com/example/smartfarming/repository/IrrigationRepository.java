package com.example.smartfarming.repository;

import com.example.smartfarming.entity.IrrigationSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationRepository extends JpaRepository<IrrigationSystem,String> {
}

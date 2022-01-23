package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Soil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoilRepository extends JpaRepository<Soil, String> {
}

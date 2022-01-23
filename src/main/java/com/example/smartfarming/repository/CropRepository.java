package com.example.smartfarming.repository;

import com.example.smartfarming.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop,String> {
    Optional<Crop> findByName(String name);

}

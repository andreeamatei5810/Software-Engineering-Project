package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class HomeDto {

    Double averageLeafTemp;
    Double averageSoilMoisture;
    Double percentageNitratesNormal;
    List<LocalDateTime> densityAnomalies;

}

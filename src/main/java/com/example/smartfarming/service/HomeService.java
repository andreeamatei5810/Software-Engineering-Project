package com.example.smartfarming.service;

import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.dto.HomeDto;
import com.example.smartfarming.dto.SoilDto;
import com.example.smartfarming.repository.CropRepository;
import com.example.smartfarming.repository.SoilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {

    final SoilRepository soilRepository;
    final CropRepository cropRepository;

    public HomeDto getReport(LocalDate date, String email) {

        List<SoilDto> soilDtos = soilRepository.findAllByTimeStampAndEmail(date.atStartOfDay(), email).stream().map(SoilDto::new).collect(Collectors.toList());
        List<CropDto> cropDtos = cropRepository.findAllByTimeStampAndEmail(date.atStartOfDay(), email).stream().map(CropDto::new).collect(Collectors.toList());
        Double averageLeafTemp = cropDtos.stream().map(CropDto::getLeafTemperature).mapToLong(Long::longValue).average().orElseThrow();
        Double averageSoilMoisture = soilDtos.stream().map(SoilDto::getMoisture).mapToInt(Integer::intValue).average().orElseThrow();
        Double percentageNitratesNormal = soilDtos.stream()
                .map(SoilDto::getResidualNitrates)
                .mapToInt(nitrate -> (nitrate >= 5 && nitrate <= 20) ? 1 : 0)
                .average()
                .orElseThrow() * 100;
        List<LocalDateTime> densityAnomalies = soilDtos.stream()
                .filter(soil -> (soil.getDensity() < 1.2 && soil.getDensity() > 2.0))
                .map(SoilDto::getTimeStamp)
                .collect(Collectors.toList());

        return new HomeDto()
                .setAverageLeafTemp(averageLeafTemp)
                .setAverageSoilMoisture(averageSoilMoisture)
                .setDensityAnomalies(densityAnomalies)
                .setPercentageNitratesNormal(percentageNitratesNormal);


    }
}

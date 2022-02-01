package com.example.smartfarming.controller;

import com.example.smartfarming.dto.HomeDto;
import com.example.smartfarming.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    final HomeService homeService;

    @GetMapping
    public ResponseEntity<HomeDto> home(@RequestParam(value = "date", required = false, defaultValue = "#{T(java.time.LocalDate).now().minusMonths(6)}") LocalDate date, Principal principal) {
        return ResponseEntity.ok().body(homeService.getReport(date,principal.getName()));
    }

}

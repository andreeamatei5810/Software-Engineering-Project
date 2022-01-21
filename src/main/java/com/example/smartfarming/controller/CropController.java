package com.example.smartfarming.controller;

import com.example.smartfarming.dto.ClientRegister;
import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.dto.MqttSubscribeModel;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.service.ClientService;
import com.example.smartfarming.service.CropService;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

    final CropService cropService;

    @PostMapping("/addCrop")
    public ResponseEntity<String> saveCrops (@RequestBody CropDto cropDto) throws MqttException {
        return ResponseEntity.ok().body(cropService.saveCrop(cropDto));
    }

    @GetMapping("/showCrops")
    public List<CropDto> showCrops () {
        return cropService.showCrops();
    }

    @GetMapping("show/{name}")
    public CropDto showCrop(@PathVariable String name){
        return cropService.showCrop(name);
    }

    /*
    @GetMapping("/add")
    public ResponseEntity<String> saveCrops () throws MqttException {
        return ResponseEntity.ok().body(cropService.saveCrops());
    }
    */

}

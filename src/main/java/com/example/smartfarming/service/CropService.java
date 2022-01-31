package com.example.smartfarming.service;

import com.example.smartfarming.dto.CropDto;
import com.example.smartfarming.entity.Crop;
import com.example.smartfarming.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CropService {

    final CropRepository cropRepository;
    final MessagingService messagingService;

    @PostConstruct
    public String saveCrops() throws MqttException {
        ArrayList<CropDto> crops = readCropsFromJson();
        for (CropDto cropDto : crops) {
            saveCrop(cropDto);
        }
        return "The data has been retrieved.";
    }

    public String saveCrop(CropDto cropDto) throws MqttException{
        Optional<Crop> cropOptional = cropRepository.findByName(cropDto.getName());
        if (cropOptional.isEmpty()){
            Crop crop = new Crop();
            BeanUtils.copyProperties(cropDto, crop);
            crop.setId(UUID.randomUUID().toString());
            cropRepository.save(crop);
            publishCrops(crop);
            return "Crop is saved.";
        }
        return "Crop couldn't be saved.";
    }

    public  List<CropDto> showCrops(){
        List<CropDto> cropDtoArrayList = new ArrayList<>();
        List<Crop> cropArrayList = cropRepository.findAll();

        for (Crop crop : cropArrayList) {
            CropDto cropDto = new CropDto();
            BeanUtils.copyProperties(crop, cropDto);
            cropDtoArrayList.add(cropDto);
        }

        return cropDtoArrayList;
    }

    private void publishCrops(Crop crop) throws MqttException {
        String message = crop.toString();
        messagingService.publish("crop", message,0, true);
    }

    public ArrayList<CropDto> readCropsFromJson() {

        ArrayList<CropDto> cropArrayList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/data/crop.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray cropList = (JSONArray) obj;

            for (Object o : cropList) {
                JSONObject cropObject = (JSONObject) o;
                CropDto cropElement = new CropDto((String) cropObject.get("name"), (Long) cropObject.get("height"), (String) cropObject.get("leaf color"), (Long) cropObject.get("leaf temperature"));
                cropArrayList.add(cropElement);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return cropArrayList;
    }
}

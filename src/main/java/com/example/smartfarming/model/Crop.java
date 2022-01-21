package com.example.smartfarming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Crop {

    @Id
    String id;
    String name;
    Float height ;
    String leafColor;
    Float leafTemperature;

}

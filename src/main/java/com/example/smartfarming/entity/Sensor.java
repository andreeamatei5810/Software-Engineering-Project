package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Sensor {

    @Id
    String id;
    String clientId;

}

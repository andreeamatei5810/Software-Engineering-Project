package com.example.smartfarming.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification {

    @Id
    String id;
    String message;
    LocalDateTime timeStamp;

}

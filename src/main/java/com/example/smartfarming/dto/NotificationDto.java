package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDto {

    String clientId;
    String message;
    LocalDateTime timeStamp;
    Boolean read;

}

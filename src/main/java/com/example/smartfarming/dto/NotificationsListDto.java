package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotificationsListDto {

    Integer numberOfNewMessages;
    List<NotificationDto> notifications;
}

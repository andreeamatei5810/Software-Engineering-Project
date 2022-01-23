package com.example.smartfarming.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PublishMessage {

    @NotNull
    private String topic;

    @NotNull
    private String message;

    @NotNull
    private Boolean retained;

    @NotNull
    private Integer qos;

}

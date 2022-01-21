package com.example.smartfarming.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
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

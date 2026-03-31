package com.zerotrust.k8s.eventservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventCreateRequestDto {
    @NotBlank
    private String type;

    @NotBlank
    private String message;
}

package com.zerotrust.k8s.eventservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class EventResponseDto {

    private String id;
    private String type;
    private String message;
    private LocalDateTime createdAt;
}

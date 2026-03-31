package com.zerotrust.k8s.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerResponseDto {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String deviceNumber;
}
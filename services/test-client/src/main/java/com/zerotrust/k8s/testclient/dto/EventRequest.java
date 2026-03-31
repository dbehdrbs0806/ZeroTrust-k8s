package com.zerotrust.k8s.testclient.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {

    private String type;
    private String message;
}

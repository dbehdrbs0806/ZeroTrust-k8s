package com.zerotrust.k8s.userservice.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {
    // entity 역할
    @Id
    private String id;

    private String name;

    private String email;

    private String phone;

    private String deviceNumber;
}

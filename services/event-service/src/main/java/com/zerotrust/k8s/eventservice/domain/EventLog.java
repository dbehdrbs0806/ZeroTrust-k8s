package com.zerotrust.k8s.eventservice.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_log")
public class EventLog {

    @Id
    private String id;

    private String type;
    private String message;
    private LocalDateTime createdAt;
}

package com.zerotrust.k8s.testclient.controller;


import com.zerotrust.k8s.testclient.dto.EventRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RestClient restClient = RestClient.create();

    @Value("${service.user.url}")
    private String userServiceUrl;

    @Value("${service.event.url}")
    private String eventServiceUrl;

    @GetMapping("/users")
    public ResponseEntity<String> callUsers() {
        String responseBody = restClient.get()
                .uri(userServiceUrl + "/api/customers")
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/events")
    public ResponseEntity<String> callEvents(@RequestBody EventRequest request) {
        String responseBody = restClient.post()
                .uri(eventServiceUrl + "/api/events")
                .body(request)
                .retrieve()
                .body(String.class);

        return ResponseEntity.ok(responseBody);
    }
}

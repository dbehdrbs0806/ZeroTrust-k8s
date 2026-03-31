package com.zerotrust.k8s.eventservice.controller;

import com.zerotrust.k8s.eventservice.dto.EventCreateRequestDto;
import com.zerotrust.k8s.eventservice.dto.EventResponseDto;
import com.zerotrust.k8s.eventservice.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDto create(@Valid @RequestBody EventCreateRequestDto request) {
        return eventService.create(request);
    }

    @GetMapping
    public List<EventResponseDto> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventResponseDto findById(@PathVariable String id) {
        return eventService.findById(id);
    }
}

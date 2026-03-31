package com.zerotrust.k8s.eventservice.service;


import com.zerotrust.k8s.eventservice.domain.EventLog;
import com.zerotrust.k8s.eventservice.dto.EventCreateRequestDto;
import com.zerotrust.k8s.eventservice.dto.EventResponseDto;
import com.zerotrust.k8s.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDto create(EventCreateRequestDto request) {
        EventLog eventLog = EventLog.builder()
                .type(request.getType())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        EventLog saved = eventRepository.save(eventLog);
        return toResponse(saved);
    }

    public List<EventResponseDto> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EventResponseDto findById(String id) {
        EventLog eventLog = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다. id=" + id));

        return toResponse(eventLog);
    }

    private EventResponseDto toResponse(EventLog eventLog) {
        return EventResponseDto.builder()
                .id(eventLog.getId())
                .type(eventLog.getType())
                .message(eventLog.getMessage())
                .createdAt(eventLog.getCreatedAt())
                .build();
    }
}

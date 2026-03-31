package com.zerotrust.k8s.userservice.controller;


import com.zerotrust.k8s.userservice.dto.CustomerCreateRequestDto;
import com.zerotrust.k8s.userservice.dto.CustomerResponseDto;
import com.zerotrust.k8s.userservice.dto.CustomerUpdateRequestDto;
import com.zerotrust.k8s.userservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto create(@Valid @RequestBody CustomerCreateRequestDto request) {
        return customerService.create(request);
    }

    @GetMapping
    public List<CustomerResponseDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto findById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(@PathVariable String id,
                                   @Valid @RequestBody CustomerUpdateRequestDto request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable String id) {
        customerService.delete(id);
        return Map.of("message", "삭제 완료", "id", id);
    }
}
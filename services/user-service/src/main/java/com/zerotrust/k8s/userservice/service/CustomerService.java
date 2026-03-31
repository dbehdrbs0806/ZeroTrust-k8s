package com.zerotrust.k8s.userservice.service;


import com.zerotrust.k8s.userservice.domain.Customer;
import com.zerotrust.k8s.userservice.dto.CustomerCreateRequestDto;
import com.zerotrust.k8s.userservice.dto.CustomerResponseDto;
import com.zerotrust.k8s.userservice.dto.CustomerUpdateRequestDto;
import com.zerotrust.k8s.userservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Customer Create 생성 함수
    public CustomerResponseDto create(CustomerCreateRequestDto request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .deviceNumber(generateDeviceNumber())
                .build();

        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }
    // findAll() Custom 전체 출력
    public List<CustomerResponseDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // findById() Id 기반해서 find() 찾는 함수
    public CustomerResponseDto findById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다. id=" + id));

        return toResponse(customer);
    }

    //
    public CustomerResponseDto update(String id, CustomerUpdateRequestDto request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다. id=" + id));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer updated = customerRepository.save(customer);
        return toResponse(updated);
    }

    public void delete(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다. id=" + id));

        customerRepository.delete(customer);
    }

    private CustomerResponseDto toResponse(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .deviceNumber(customer.getDeviceNumber())
                .build();
    }

    private String generateDeviceNumber() {
        return "DEV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }



}

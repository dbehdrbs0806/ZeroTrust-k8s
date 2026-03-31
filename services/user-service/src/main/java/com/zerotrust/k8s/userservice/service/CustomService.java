package com.zerotrust.k8s.userservice.service;


import com.zerotrust.k8s.userservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomService {

    private final CustomerRepository customerRepository;

    // Customer Create 생성 함수
    public CustomerResponse create(CustomerCreateRequest request) {
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
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // findById() Id 기반해서 find() 찾는 함수
    public CustomerResponse findById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다. id=" + id));

        return toResponse(customer);
    }

    //
    public CustomerResponse update(String id, CustomerUpdateRequest request) {
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

    private CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
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

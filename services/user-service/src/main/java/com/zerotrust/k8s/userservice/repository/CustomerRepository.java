package com.zerotrust.k8s.userservice.repository;

import com.zerotrust.k8s.userservice.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}

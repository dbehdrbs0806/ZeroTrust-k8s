package com.zerotrust.k8s.eventservice.repository;

import com.zerotrust.k8s.eventservice.domain.EventLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventLog, String> {
}

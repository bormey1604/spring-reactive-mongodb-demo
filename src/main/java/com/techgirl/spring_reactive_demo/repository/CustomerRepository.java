package com.techgirl.spring_reactive_demo.repository;

import com.techgirl.spring_reactive_demo.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Flux<Customer> findAllByJob(String job);
}

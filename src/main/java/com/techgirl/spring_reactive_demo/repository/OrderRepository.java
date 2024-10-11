package com.techgirl.spring_reactive_demo.repository;

import com.techgirl.spring_reactive_demo.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findAllByCustomerId(String customerId);
}

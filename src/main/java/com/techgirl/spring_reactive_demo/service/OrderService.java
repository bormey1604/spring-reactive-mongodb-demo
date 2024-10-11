package com.techgirl.spring_reactive_demo.service;

import com.techgirl.spring_reactive_demo.model.Order;
import com.techgirl.spring_reactive_demo.repository.CustomerRepository;
import com.techgirl.spring_reactive_demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<Order> createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Mono<Map<String, Double>> calculateSummary(){
        return customerRepository.findAll()
                .flatMap(customer -> Mono.zip(Mono.just(customer), calculateOrderSum(customer.getId())))
                .collectMap(tuple2 -> tuple2.getT1().getName(), Tuple2::getT2);
    }


    private Mono<Double> calculateOrderSum(String customerId){
        Flux<Order> findCustomerOrders = orderRepository.findAllByCustomerId(customerId);

        return findCustomerOrders.map(Order::getTotal)
                .reduce(0d,Double::sum);
    }

}

package com.techgirl.spring_reactive_demo.controller;


import com.techgirl.spring_reactive_demo.model.Order;
import com.techgirl.spring_reactive_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/sales/summary")
    public Mono<Map<String, Double>> calculateSummary(){
        return orderService.calculateSummary();
    }


}

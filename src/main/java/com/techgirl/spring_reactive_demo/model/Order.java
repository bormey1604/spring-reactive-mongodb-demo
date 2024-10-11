package com.techgirl.spring_reactive_demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String id;
    private String customerId;
    private double total;
    private double discount;

    public Order(double discount, String customerId, double total) {
        this.id = UUID.randomUUID().toString();
        this.discount = discount;
        this.customerId = customerId;
        this.total = total;
    }
}

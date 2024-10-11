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
public class Customer {

    @Id
    private String id;
    private String name;
    private String job;

    public Customer( String name, String job) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.job = job;
    }
}

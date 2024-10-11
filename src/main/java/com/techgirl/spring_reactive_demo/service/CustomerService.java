package com.techgirl.spring_reactive_demo.service;

import com.techgirl.spring_reactive_demo.model.Customer;
import com.techgirl.spring_reactive_demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Mono<Customer> createCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public Flux<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }


    public Mono<Customer> findCustomerById(String id){
        return customerRepository.findById(id).log();
    }

    public Flux<Customer> findByJob(String job){
        return customerRepository.findAllByJob(job);
    }
}

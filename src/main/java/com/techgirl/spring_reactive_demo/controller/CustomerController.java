package com.techgirl.spring_reactive_demo.controller;


import com.techgirl.spring_reactive_demo.model.Customer;
import com.techgirl.spring_reactive_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Mono<Customer> createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("/getAll")
    public Flux<Customer> findAllCustomer(){
        return customerService.findAllCustomer();
    }

    @GetMapping("/getCustomer/{id}")
    public Mono<Customer> findCustomerById(@PathVariable String id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/getCustomer/filter")
    public Flux<Customer> findByJob(@RequestParam String job){
        return customerService.findByJob(job);
    }

}


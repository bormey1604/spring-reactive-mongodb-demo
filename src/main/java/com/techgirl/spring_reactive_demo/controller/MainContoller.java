package com.techgirl.spring_reactive_demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class MainContoller {

    @GetMapping()
    public Mono<String> handleMain(){
        return Mono.just("home");
    }

}

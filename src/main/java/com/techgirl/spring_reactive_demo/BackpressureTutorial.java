package com.techgirl.spring_reactive_demo;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackpressureTutorial {

    private Flux<Long> createNoOverflowFlux(){
        return  Flux.range(1, Integer.MAX_VALUE)
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)));  //simulate that processing takes time
    }

    private Flux<Long> createOverflowFlux(){
        return  Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)));
    }

    private Flux<Long> createDropOnBackpressureFlux(){
        return  Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(x -> System.out.println("Element kept by consumer:"+x));
    }

    private Flux<Long> createBufferOnBackpressureFlux(){
        return  Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50, BufferOverflowStrategy.DROP_LATEST)
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(x -> System.out.println("Element kept by consumer: "+x));
    }


    public static void main(String[] args) {
        BackpressureTutorial backpressureTutorial = new BackpressureTutorial();
        backpressureTutorial.createBufferOnBackpressureFlux().blockLast();
    }
}

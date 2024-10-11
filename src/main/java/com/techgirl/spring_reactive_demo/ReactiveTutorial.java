package com.techgirl.spring_reactive_demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReactiveTutorial {

    private Mono<String> testMono(){
        return Mono.just("test")
                .log();
    }

    private Flux<String> testFlux(){
        List<String> programmingLanguage = List.of("Java","C#","Python","Dart");

        return Flux.fromIterable(programmingLanguage);
    }

    private Flux<String> testMap(){
        Flux<String> stringFlux = Flux.just("Java","C#","Python","Dart");
        return stringFlux.map(s -> s.toUpperCase(Locale.ROOT)); //return simple data
    }

    private Flux<String> testFlatMap(){
        Flux<String> flux = Flux.just("Java","C#","Python","Dart");
        return flux.flatMap( s-> Mono.just(s.toUpperCase(Locale.ROOT) )) ; //return publisher
    }

    private Flux<String> testSkip(){
        Flux<String> flux = Flux.just("Java","C#","Python","Dart")
                .delayElements(Duration.ofSeconds(1));

//        flux.skip(2);
//        flux.skip(Duration.ofMillis(2000));
        return flux.skipLast(1);
    }

    private Flux<Integer> testComplexSkip(){
        Flux<Integer> flux = Flux.range(1,20);

//        flux.skipWhile( integer -> integer < 10 );
        return flux.skipUntil(integer -> integer == 15); //skip until condition true
    }

    private Flux<Integer> testConcat(){
        Flux<Integer> flux1 = Flux.range(1,20);
        Flux<Integer> flux2 = Flux.range(101,20);

        return Flux.concat(flux1, flux2);
    }

    private Flux<Integer> testMerge(){
        Flux<Integer> flux1 = Flux.range(1,20);
        Flux<Integer> flux2 = Flux.range(101,20);

        return Flux.merge(flux1, flux2);
    }

    private Flux<Tuple2<Integer,Integer>> testZip(){
        Flux<Integer> flux1 = Flux.range(1,20);
        Flux<Integer> flux2 = Flux.range(101,20);

        return Flux.zip(flux1, flux2);
    }

    private Flux<Tuple3<Integer,Integer,Integer>> testComplexZip(){
        Flux<Integer> flux1 = Flux.range(1,10);
        Flux<Integer> flux2 = Flux.range(101,20);
        Flux<Integer> flux3 = Flux.range(1001,20);

        return Flux.zip(flux1, flux2, flux3);
    }

    private Mono<List<Integer>> testCollection(){         //collect elements into single list return Mono<List<T>>
        Flux<Integer> flux = Flux.range(1,10);

        return flux.collectList();
    }

    private Flux<List<Integer>> testBuffer(){       //collect elements into list return Flux<List<T>>
        Flux<Integer> flux = Flux.range(1,10);
        return flux.buffer(3);
    }

    private Mono<Map<Integer,Integer>> testMapCollection(){

        Flux<Integer> flux = Flux.range(1,10);
        return flux.collectMap( integer -> integer, integer -> integer * integer);
    }

    private Flux<Integer> testDoFunctions(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnEach( signal -> {
            if(signal.getType() ==  SignalType.ON_COMPLETE){
                System.out.println("I am done!");
            }else{
                System.out.println(signal.get());
            }

        });
    }

    private Flux<Integer> testDoFunctions2(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnComplete(() -> System.out.println("I am completed!"));
    }

    private Flux<Integer> testDoFunctions3(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnNext( integer -> System.out.println(integer));
    }

    private Flux<Integer> testDoOnSubscribe(){
        Flux<Integer> flux = Flux.range(1,10);
        return flux.doOnSubscribe( subscription -> System.out.println( subscription));
    }

    private Flux<Integer> testDoOnCancel(){
        Flux<Integer> flux = Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1));
        return flux.doOnCancel( () -> System.out.println("Cancelled!"));
    }

    private Flux<Integer> testErrorHandling(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number!");
                    }
                    return integer;
                });

        return flux.onErrorContinue((throwable,o) -> System.out.println("Dont worry anout = " + 5));
    }

    private Flux<Integer> testErrorHandling2(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number!");
                    }
                    return integer;
                });

        return flux.onErrorReturn(-1);
    }

    private Flux<Integer> testErrorHandling3(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number!");
                    }
                    return integer;
                });

        return flux.onErrorResume(throwable -> Flux.range(100,5));  //return new Flux
    }

    private Flux<Integer> testErrorHandling4(){
        Flux<Integer> flux = Flux.range(1,10)
                .map(integer -> {
                    if(integer == 5){
                        throw new RuntimeException("Unexpected number!");
                    }
                    return integer;
                });

        return flux.onErrorMap(throwable -> new UnsupportedOperationException(throwable.getMessage()) );  //return another type of exception
    }




    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial reactiveTutorial = new ReactiveTutorial();

        reactiveTutorial.testErrorHandling4().subscribe(System.out::println);  //asyncronous

//         List<Integer> output = reactiveTutorial.testCollection().block();    //syncronous
//        System.out.println("output = " + output);

//        Thread.sleep(3_500);
    }
}

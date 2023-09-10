package com.budhlani.reactiveprogramming;

import io.micrometer.observation.Observation;
import reactor.core.publisher.Flux;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 11. Ways of Creating Flux Programmatically (Or based on Conditions )
 * Flux.create(fluxSink ->
 * fluxsink.create() --- used for creating n number of items
 * fluxSink.complete --- to End the Flux Creation .
 * 12 . Take operator is just a operator like map . For ex flux.take(3) means it will take 3 items.
 * 13. There is a limitation with take operator in flux.take as it it don't cancel on take operator and solution is we have to check like !fluxSink.isCancelled().
 * 14. Another way of generating Flux is Flux.generate(synchronousink -> {
 * ss.next() // This will keep on emitting items.
 * }
 * --> We can use take to limit the number of items.
 * --> We can use if loop and synchsink.complete() inside the if condition.
 *
 * 15. Study flux.generate state maintenance for counter(loop) problems.
 */

public class OperatorsInRactiveProgramming {
    public static void main(String[] args) {
        // 1. Handle Operator --> It is mix of filter and map (taking stream example for reference)
//        Flux.fromArray(new String[]{"Vaibhav", "Garima"})
//                .handle((s, synchronousSink) -> {
//                 synchronousSink.next(s);
//        }).subscribe(getObjectConsumer(),
//                        System.out::println,
//                        () -> System.out.println("Completed"));

        Flux.fromStream(Stream.of(1,2,3,4,5,6)).handle((integer, synchronousSink) -> {
            //synchronousSink.next(integer+3);
            System.out.println(integer+3);

        }).subscribe(getObjectConsumer(),
                System.out::println,
                () -> System.out.println("Completed"));




    }

    private static Consumer<Object> getObjectConsumer() {
        return o -> System.out.println(o);
    }



    private static void printresult(Object o){
            System.out.println(o);
    }

    private static Consumer<String> getDataConsumer(){
        return s -> System.out.println(s);
    }




}

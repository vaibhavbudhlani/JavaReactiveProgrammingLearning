package com.budhlani.reactiveprogramming;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class TestFlux {

    private Faker faker = new Faker();

    private static CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) {
        Integer a[] = {1,2,3,4};
        int[] vb ={5,6,7,8};

        Flux<Integer> flux1 = Flux.just(1);
        Flux<Integer> flux2 = Flux.fromArray(a);
        Flux<Integer> flux3 = Flux.fromStream(()-> Arrays.stream(a));
        Flux<Long> flux4 = Flux.interval(Duration.ofSeconds(1)); // Not getting any result
        Flux<Integer> flux5 = Flux.range(2, 10);
        TestFlux obj = new TestFlux();
       String ans = null;


        obj.getData()
                .doOnComplete(() -> latch.countDown())
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .map(value -> {
                    //ans = value;
                    return value;
                })
                .subscribe();
//                .subscribe(s-> {
//                    if(s>106 || s < 100){
//                        latch.countDown();
//                    }
//                    else{
//                        ans = s.toString();
//                    }
//                        },
//                err-> System.out.println(err),
//                () -> System.out.println("completed"));
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public  Flux<Integer> getData(){
        AtomicInteger aI = new AtomicInteger(100);
          return Flux.interval(Duration.ofSeconds(1))
                  .map(i-> aI.accumulateAndGet(faker.random().nextInt(-5,5),
                          (a, b)-> a+b));
    }
}

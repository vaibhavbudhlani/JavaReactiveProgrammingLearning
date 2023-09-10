package com.budhlani.reactiveprogramming;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class ReadFileFlux {

    public Consumer<Integer> consumer;
    public Publisher publisher;

    public static void main(String[] args) {
        ReadFileFlux readFileFlux = new ReadFileFlux();
        System.out.println(Arrays.stream(new Integer[]{1, 2, 3, 4}).filter(ReadFileFlux::filterItems)
                .map(ReadFileFlux::mapping).reduce((a,b) -> a+b).get());

        readFileFlux.getFileContent()
                //   .take(1)
                .subscribe(System.out::println);
    }

    private Callable<BufferedReader> getBuffereader() {
        InputStream is = ReadFileFlux.class.getResourceAsStream("/files/fruits.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return () -> reader;
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> getObject() {
        return (br, sink) -> {
            try {
                String line = br.readLine();
                if (Objects.isNull(line)) {
                    sink.complete();
                } else
                    sink.next(line);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return br;
        };
    }

    public Flux<String> getFileContent() {

//        return Flux.generate(()-> 1 ,(integer, stringSynchronousSink) -> {
//           String vb = "Vaibhav";
//           stringSynchronousSink.next(vb);
//           if(integer>3){
//               stringSynchronousSink.complete();
//           }
//            return integer+1;
//        }, integer -> {
//
//        });
        return Flux.generate(getBuffereader(), getObject(),
                bufferedReader -> {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private static Predicate<Integer> getIntPredicate() {

        return vb -> vb > 2;
    }

    private static Boolean filterItems(int value){
           return value > 3;
    }

    private static Integer mapping(int value){
         return value *3;
    }

    private Consumer<Integer> consumer(){
        return integer -> System.out.println(integer);
    }
}

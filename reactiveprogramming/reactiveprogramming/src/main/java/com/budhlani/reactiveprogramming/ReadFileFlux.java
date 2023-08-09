package com.budhlani.reactiveprogramming;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ReadFileFlux {

    public Consumer<Integer> consumer;
    public Publisher publisher;

    public static void main(String[] args) {
        ReadFileFlux readFileFlux = new ReadFileFlux();

        readFileFlux.getFileContent()
                //   .take(1)
                .subscribe(s -> System.out.println(s));
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
}

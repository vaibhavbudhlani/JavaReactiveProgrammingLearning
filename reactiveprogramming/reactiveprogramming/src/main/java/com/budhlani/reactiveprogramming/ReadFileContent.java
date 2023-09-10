package com.budhlani.reactiveprogramming;

import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileContent {

    public static Path PATH = Paths.get("src/main/resources/files");
    public static void main(String[] args) {


       // When unsure related to response use from Supplier
        Mono.fromSupplier(() -> getFileContent() )
                .subscribe(s -> {
                    System.out.println(s);
                });



    }

    public static String getName(){
        System.out.println("Coming inside Method");
        return "Vaibhav Name from Method";
    }

    public static String getFileContent(){
        InputStream is = ReadFileContent.class.getResourceAsStream("/files/fruits.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String ans = "";
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            //System.out.println(line);
            ans += line;
        }
        return ans;

    }



}

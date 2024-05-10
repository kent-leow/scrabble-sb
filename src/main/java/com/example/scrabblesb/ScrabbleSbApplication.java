package com.example.scrabblesb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrabbleSbApplication {
    @Value("${scrabble.front-end.url}")
    private String[] frontEndApplicationUrl;

    public static void main(String[] args) {
        SpringApplication.run(ScrabbleSbApplication.class, args);
    }
}

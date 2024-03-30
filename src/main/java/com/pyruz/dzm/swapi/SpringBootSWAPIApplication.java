package com.pyruz.dzm.swapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootSWAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSWAPIApplication.class, args);
    }
}

package com.pyruz.dzm.swapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSWAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSWAPIApplication.class, args);
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger();
    }

}

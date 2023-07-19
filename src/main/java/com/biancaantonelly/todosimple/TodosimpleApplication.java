package com.biancaantonelly.todosimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.biancaantonelly.todosimple")
public class TodosimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodosimpleApplication.class, args);
    }
}

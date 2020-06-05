package com.example.lab4;

import com.example.lab4.configuration.WebConfiguration;
import com.example.lab4.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {

    SpringApplication.run(Lab4Application.class, args);
    }

}

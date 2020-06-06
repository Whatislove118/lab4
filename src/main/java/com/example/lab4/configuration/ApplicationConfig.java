package com.example.lab4.configuration;

import com.example.lab4.beanpostprocessors.InitMBeanBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationConfig {

    @Bean
    public InitMBeanBeanPostProcessor getInitMbeanPostProcessor(){
        return  new InitMBeanBeanPostProcessor();
    }
}

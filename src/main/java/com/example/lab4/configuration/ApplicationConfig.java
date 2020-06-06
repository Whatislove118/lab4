package com.example.lab4.configuration;

import com.example.lab4.beanpostprocessors.InitMBeanBeanPostProcessor;
import com.example.lab4.entity.User;
import com.example.lab4.profilingandmonitoring.MBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class ApplicationConfig {

    @Bean
    public InitMBeanBeanPostProcessor getInitMbeanPostProcessor(){
        return  new InitMBeanBeanPostProcessor();
    }
}

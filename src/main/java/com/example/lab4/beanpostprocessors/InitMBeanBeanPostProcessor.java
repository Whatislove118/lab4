package com.example.lab4.beanpostprocessors;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.service.PointService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class InitMBeanBeanPostProcessor implements BeanPostProcessor {


    @Autowired
    private PointService pointService;

    private Field[] fields;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean.getClass().getAnnotation(InitMBean.class) != null) {
                System.out.println("Initilize MBeans");
                fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    switch (field.getName()) {
                        case "allPoints":
                            ReflectionUtils.setField(field, bean, pointService.findAllPoints());
                            break;
                        case "hitPoints":
                            ReflectionUtils.setField(field, bean, pointService.findByIsAreaTrue());
                            break;
                        case "percent":
                            ReflectionUtils.setField(field,bean,pointService.getHitPercents());
                            break;
                    }
                }
            }
        return bean;
    }
}

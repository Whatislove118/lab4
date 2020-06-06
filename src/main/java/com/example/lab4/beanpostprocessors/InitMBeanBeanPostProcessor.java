package com.example.lab4.beanpostprocessors;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.entity.User;
import com.example.lab4.profilingandmonitoring.MBean;
import com.example.lab4.repository.PointRepository;
import com.example.lab4.service.PointService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class InitMBeanBeanPostProcessor implements BeanPostProcessor {


    @Autowired
    private PointRepository pointRepository;

    private Field[] fields;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Field f = null;
        if (bean.getClass().equals(MBean.class)) {
            System.out.println("Initilize MBean");
            try {
                f = bean.getClass().getDeclaredField("user");
                f.setAccessible(true);
                fields = bean.getClass().getDeclaredFields();
                if (bean.getClass().getAnnotation(InitMBean.class) != null) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        switch (field.getName()){
                            case "allPoints":
                                ReflectionUtils.setField(field, bean, pointRepository.findByUser((User) f.get(bean)).size());
                                break;
                            case "hitPoints":
                                ReflectionUtils.setField(field,bean,pointRepository.findByUserAndIsAreaTrue((User) f.get(bean)).size());
                                break;
                            case "percent":
                                ReflectionUtils.setField(field,bean,(pointRepository.findByUser((User) f.get(bean)).size()*1.0 /pointRepository.findByUserAndIsAreaTrue((User) f.get(bean)).size() * 1.0) * 100 );
                                break;
                        }
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}

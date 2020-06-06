package com.example.lab4.controllers;

import com.example.lab4.profilingandmonitoring.CounterPercent;
import com.example.lab4.profilingandmonitoring.CounterPoints;
import com.example.lab4.profilingandmonitoring.SimpleAgent;
import com.example.lab4.service.MBeanService;
import com.example.lab4.tools.CheckArea;
import com.example.lab4.entity.Point;
import com.example.lab4.entity.SimplePoint;
import com.example.lab4.repository.PointRepository;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class PointController {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private UserService userService;
    private MBeanService mBeanService;
    private final SimpleAgent simpleAgent;
    private final ApplicationContext applicationContext;


    public PointController(PointRepository pointRepository, UserRepository userRepository, UserService userService, MBeanService mBeanService, SimpleAgent simpleAgent, ApplicationContext applicationContext) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.mBeanService = mBeanService;
        this.simpleAgent = simpleAgent;
        this.applicationContext = applicationContext;
    }

    @CrossOrigin
    @RequestMapping("/checkpoints")
    @JsonFormat
    public Collection<SimplePoint> newPoint(@RequestBody Point newPoint) {
        newPoint.setInArea(CheckArea.isInArea(newPoint));
        System.out.println(newPoint.toString());
        try {
            userService.findByLogin(newPoint.getUser().getLogin());
        } catch (NullPointerException e) {
            System.out.println("USER is not found!");
            return null;
        }
        newPoint.setUser(userService.findByLogin(newPoint.getUser().getLogin()));
        pointRepository.save(newPoint);
        Collection<SimplePoint> collection = new ArrayList<>();
        for (Point point:pointRepository.findByUser(userRepository.findByLogin(newPoint.getUser().getLogin()))) {
            collection.add(point.convertToSimplePoint());
        }
        CounterPoints counterPoints = applicationContext.getBean("counterPoints",CounterPoints.class);
        CounterPercent counterPercent = applicationContext.getBean("counterPercent", CounterPercent.class);
        mBeanService.initCounterPoints(counterPoints);
        mBeanService.initCounterPercent(counterPercent);
        System.out.println(counterPoints);
        System.out.println(counterPercent);
        counterPoints.isValidate(CheckArea.isValidateArea(newPoint),newPoint);
        return collection;
    }
}




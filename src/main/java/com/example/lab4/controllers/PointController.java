package com.example.lab4.controllers;

import com.example.lab4.tools.CheckArea;
import com.example.lab4.UserDetailsServ;
import com.example.lab4.entity.Point;
import com.example.lab4.entity.SimplePoint;
import com.example.lab4.repository.PointRepository;
import com.example.lab4.repository.UserRepository;
import com.example.lab4.service.PointService;
import com.example.lab4.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class PointController {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    PointController(PointRepository pointRepository, UserRepository userRepository) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
    }
    @Autowired
    private CheckArea checkArea;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsServ userDetailsServ;

    @Autowired
    private PointService pointService;


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
        return collection;
    }





//    @CrossOrigin
//    @RequestMapping("/getpoints")
//    @JsonFormat
//    public Collection<SimplePoint> printAllPoints(@RequestBody User user){
//        System.out.println("Send table of  "+user.getLogin());
//        Collection<SimplePoint> collection = new ArrayList<>();
//        for (Point point:pointRepository.findByUser(userRepository.findByLogin(user.getLogin()))) {
//            collection.add(point.convertToSimplePoint());
//        }
//        return collection;
//    }


//        newPoint.setUser(userRepository.findByLogin(user.getName()));
//        System.out.println("created new point: " +newPoint);
//        return pointRepository.save(newPoint).convertToSimplePoint();






    }



package com.example.lab4.service;

import com.example.lab4.entity.Point;
import com.example.lab4.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public Point save(Point point){
        System.out.println("Point is saved");
        return pointRepository.save(point);
    }



}

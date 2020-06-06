package com.example.lab4.service;

import com.example.lab4.entity.Point;
import com.example.lab4.entity.User;
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

    public int findAllPoints(){
        return pointRepository.findAll().size();
    }

    public int findByIsAreaTrue(){
        return pointRepository.findByIsAreaTrue().size();
    }

    public double getHitPercents(){
        return (pointRepository.findByIsAreaTrue().size()*1.0 / pointRepository.findAll().size()*1.0) * 100;
    }




}

package com.example.lab4.service;

import com.example.lab4.profilingandmonitoring.CounterPercent;
import com.example.lab4.profilingandmonitoring.CounterPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MBeanService {

    @Autowired
    private PointService pointService;

    public void initCounterPoints(CounterPoints counterPoints){
        counterPoints.setAllPoints(pointService.findAllPoints());
        counterPoints.setHitPoints(pointService.findByIsAreaTrue());
    }

    public void initCounterPercent(CounterPercent counterPercent){
        counterPercent.setPercent(pointService.getHitPercents());
    }
}

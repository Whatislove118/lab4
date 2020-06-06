package com.example.lab4.service;

import com.example.lab4.profilingandmonitoring.CounterPercent;
import com.example.lab4.profilingandmonitoring.CounterPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MBeanService {

    public void initCounterPoints(CounterPoints counterPoints){
        counterPoints.countAllPoints();
        counterPoints.countHitPoints();
    }

    public void initCounterPercent(CounterPercent counterPercent){
        counterPercent.countPercent();
    }
}

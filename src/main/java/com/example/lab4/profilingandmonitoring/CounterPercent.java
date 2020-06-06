package com.example.lab4.profilingandmonitoring;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;

@InitMBean
public class CounterPercent implements CounterPercentMBean {
    private double percent;

    @Autowired
    private PointService pointService;

    public double getPercent() {
        return percent;
    }

   public void countPercent(){
       percent =  pointService.getHitPercents();
   }

    @Override
    public String toString() {
        return "CounterPercent{" +
                "percent=" + percent +
                '}';
    }
}

package com.example.lab4.profilingandmonitoring;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.entity.Point;
import com.example.lab4.service.MBeanService;
import com.example.lab4.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.*;



@InitMBean
public class CounterPoints extends NotificationBroadcasterSupport implements CounterPointsMBean{
    private int allPoints;
    private int hitPoints;

    @Autowired
    private PointService pointService;

    public int getAllPoints() {
        return allPoints;
    }

    public void countAllPoints(){
        allPoints = pointService.findAllPoints();
    }

    public void countHitPoints(){
        hitPoints = pointService.findByIsAreaTrue();
    }

    public int getHitPoints() {
        return hitPoints;
    }


    @Override
    public String toString() {
        return "CounterPoints{" +
                "allPoints=" + allPoints +
                ", hitPoints=" + hitPoints +
                '}';
    }


    @Override
    public void isValidate(boolean validateFlag, Point point) {
        if (!validateFlag){
            System.out.println("Point outside area");
            sendNotification(new Notification("Validate failed",this,   1,"Point outside are: " + point));
        }
    }

}

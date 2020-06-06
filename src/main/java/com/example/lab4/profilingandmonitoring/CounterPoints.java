package com.example.lab4.profilingandmonitoring;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.entity.Point;

import javax.management.*;

@InitMBean
public class CounterPoints extends NotificationBroadcasterSupport implements CounterPointsMBean{
    private int allPoints;
    private int hitPoints;

    public int getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
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

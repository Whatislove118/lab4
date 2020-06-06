package com.example.lab4.profilingandmonitoring;

import com.example.lab4.entity.Point;

public interface CounterPointsMBean {
    public int getAllPoints();
    public void setAllPoints(int allPoints);

    public int getHitPoints();

    public void setHitPoints(int hitPoints);

    public void isValidate(boolean validateFlag, Point point);
}

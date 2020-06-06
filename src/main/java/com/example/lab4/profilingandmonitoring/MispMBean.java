package com.example.lab4.profilingandmonitoring;

import com.example.lab4.entity.User;

public interface MispMBean {

    public void setUser(User user);

    public long getId();

    public void setId(long id);

    public int getAllPoints();

    public void setAllPoints(int allPoints);

    public int getHitPoints();

    public void setHitPoints(int hitPoints);

    public double getPercent();

    public void setPercent(double percent);
    public double countPercent();
}

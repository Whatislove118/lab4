package com.example.lab4.profilingandmonitoring;

import com.example.lab4.annotations.InitMBean;

@InitMBean
public class CounterPercent implements CounterPercentMBean {
    double percent;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "CounterPercent{" +
                "percent=" + percent +
                '}';
    }
}

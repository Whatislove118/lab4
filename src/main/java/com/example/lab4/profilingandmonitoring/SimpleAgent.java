package com.example.lab4.profilingandmonitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class SimpleAgent {
    public MBeanServer mBeanServer = null;

    public SimpleAgent(CounterPoints counterPoints,CounterPercent counterPercent) {
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName counterPercentObjectName = null;
        ObjectName counterPointsObjectName = null;

        try {
            counterPercentObjectName = new ObjectName("SimpleAgent:name=counterPercent");
            counterPointsObjectName = new ObjectName("SimpleAgent:name=counterPoints");

            mBeanServer.registerMBean(counterPercent,counterPercentObjectName);
            mBeanServer.registerMBean(counterPoints,counterPointsObjectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

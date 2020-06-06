package com.example.lab4.profilingandmonitoring;

import com.example.lab4.annotations.InitMBean;
import com.example.lab4.entity.User;

import javax.persistence.*;

@InitMBean
@Entity
public class MBean implements MispMBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int allPoints;
    private int hitPoints;
    private double percent;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public MBean() {
    }

    public MBean(User user) {
        this.user = user;
        System.out.println(this.getUser().getLogin());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    public double countPercent(){
        return ( this.getHitPoints()*1.0 / this.getAllPoints()*1.0) * 100;
    }

    @Override
    public String toString() {
        return "MBean{" +
                "allPoints=" + allPoints +
                ", hitPoints=" + hitPoints +
                ", percent=" + percent +
                ", user=" + user +
                '}';
    }
}

package com.example.lab4.entity;

import javax.persistence.*;

@Entity
@Table(name = "Points")
public class Point {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //А почему авто?
    private Long id;
    private double X;
    private double Y;
    private double R;
    private boolean isArea;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Point(){}

    public Point(double x, double y, double r) {
        X = x;
        Y = y;
        R = r;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public boolean getArea() {
        return isArea;
    }

    public void setInArea(boolean isInArea) {
        this.isArea = isInArea;
    }

    public SimplePoint convertToSimplePoint(){
        return new SimplePoint(X,Y,R,isArea);
    }
    @Override
    public String toString(){
        return "X = "+ this.X + "; Y = " + this.Y + "; R = " + this.R + "; isArea = "+this.isArea + " USER: "+this.user;
    }
}

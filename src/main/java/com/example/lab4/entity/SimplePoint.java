package com.example.lab4.entity;

public class SimplePoint {
        private double x;
        private double y;
        private double r;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }

    private boolean isInArea;

        public SimplePoint(double x, double y, double r, boolean isInArea) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.isInArea = isInArea;
        }
    }


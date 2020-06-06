package com.example.lab4.tools;

import com.example.lab4.entity.Point;

public class CheckArea {
    public static boolean isInArea(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        if(x>0 && y<0){
            return false;
        }
        if(x<=0 && y>=0){
            return y <= x + r ;
        }
        if(x>=0 && y>=0){
            return Math.pow(x, 2) + Math.pow(y,2) <= Math.pow(r, 2);
        }
        if(x<=0 && y<=0){
            return x>-r/2 && y>-r;
        }
       return false;
    }

    public static boolean isValidateArea(Point point){
        double x = point.getX();
        double y = point.getY();
        double maxR = 5.2;
        if(x < -maxR || x > maxR ||
                y > maxR || y < -maxR){
            return false;
        }
        return true;
    }

}

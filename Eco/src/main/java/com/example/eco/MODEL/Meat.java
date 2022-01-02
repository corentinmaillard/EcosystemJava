package com.example.eco.MODEL;

import javafx.scene.shape.Circle;

public class Meat {
    private double x;
    private double y;
    private double giveSatiety;
    private double radiusCircle;
    private Circle c;

    public Meat (double giveSatiety, Circle c, double radiusCircle, double x, double y){
        this.giveSatiety=giveSatiety;
        this.c=c;
        this.radiusCircle=radiusCircle;
        this.x=x;
        this.y=y;
    }

    public void draw(){
        c.setRadius(radiusCircle);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

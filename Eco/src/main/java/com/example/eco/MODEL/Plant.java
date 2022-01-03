package com.example.eco.MODEL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant implements Eatable{
    private int x;
    private int y;
    private double Health;
    private double Satiety;
    private double feelHunger = 80;
    private int rootRadius;
    private int seedRadius;
    private double radiusCircle;
    private Circle c;

    public Plant(double Health, double Satiety, int rootRadius, int seedRadius, Circle c, double radiusCircle, int x, int y){
        this.Health=Health;
        this.Satiety=Satiety;
        this.rootRadius=rootRadius;
        this.seedRadius=seedRadius;
        this.c=c;
        this.radiusCircle=radiusCircle;
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {
        this.x=x;
    }

    public void setY(int y) {
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Circle getCircle(){ return c;}

    public void setHealth(double Health){
        this.Health=Health;
    }

    public double getHealth() {
        return Health;
    }

    public void setSatiety(double Satiety){
        this.Satiety=Satiety;
    }

    public double getSatiety() {
        return Satiety;
    }

    public void draw(){
        c.setRadius(radiusCircle);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }

    public int getRootRadius(){
        return rootRadius;
    }

    public int getSeedRadius(){
        return seedRadius;
    }

    public boolean IsHungry(){
        return (Satiety<=feelHunger);
    }

    @Override
    public void setSatietyPoint(double satietyPoint) {
        this.Satiety=satietyPoint;
    }

    @Override
    public double getSatietyPoint() {
        return Satiety;
    }
}

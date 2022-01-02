package com.example.eco.MODEL;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Animal{
    private double x;
    private double y;
    private double Health;
    private double Satiety;
    private int feedRadius;
    private int visionRadius;
    private double radiusCircle;
    private int gender; // 0 is a female and 1 a male
    private Circle c;
    private double moveSpeedX;
    private double moveSpeedY;
    private int timeMove;
    private int timeRest;

    public Animal(double Health, double Satiety, int feedRadius, int visionRadius, Circle c, double radiusCircle, double x, double y, int gender, double moveSpeedX, double moveSpeedY, int timeMove, int timeRest){
        this.Health=Health;
        this.Satiety=Satiety;
        this.feedRadius=feedRadius;
        this.visionRadius=visionRadius;
        this.c=c;
        this.radiusCircle=radiusCircle;
        this.x=x;
        this.y=y;
        this.gender=gender;
        this.moveSpeedX=moveSpeedX;
        this.moveSpeedY=moveSpeedY;
        this.timeMove=timeMove;
        this.timeRest=timeRest;
    }
    public void setX(double x) {
        this.x=x;
    }

    public void setY(double y) {
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

    public void setSatiety(double satiety) {
        this.Satiety = satiety;
    }

    public double getSatiety() {
        return Satiety;
    }

    public void setColor(Color color){
        c.setFill(color);
    }

    public void draw(){
        c.setRadius(radiusCircle);
        c.setTranslateX(x);
        c.setTranslateY(y);
    }

    public void setMoveSpeedX(double moveSpeedX) {
        this.moveSpeedX = moveSpeedX;
    }

    public double getMoveSpeedX() {
        return moveSpeedX;
    }

    public void setMoveSpeedY(double moveSpeedY) {
        this.moveSpeedY = moveSpeedY;
    }

    public double getMoveSpeedY() {
        return moveSpeedY;
    }

    public void setTimeMove(int timeMove){this.timeMove = timeMove-1; }

    public int getTimeMove(){ return timeMove; }

    public void setTimeRest(int timeRest){ this.timeRest = timeRest -1; }

    public int getTimeRest(){ return timeRest; }

    public int getVisionRadius(){return visionRadius;}

    public int getFeedRadius(){return feedRadius;}

    public boolean isSearchFood(){return (Satiety<60);}

}

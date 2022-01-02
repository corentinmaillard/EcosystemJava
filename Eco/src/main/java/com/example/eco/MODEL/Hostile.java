package com.example.eco.MODEL;

import javafx.scene.shape.Circle;

public class Hostile extends Animal{

    public Hostile(double Health, double Satiety, int feedRadius, int visionRadius, Circle c, double radiusCircle, double x, double y, int gender, double moveSpeedX, double moveSpeedY, int timeMove, int timeRest) {
        super(Health, Satiety, feedRadius, visionRadius, c, radiusCircle, x, y, gender, moveSpeedX, moveSpeedY, timeMove, timeRest);
    }

}

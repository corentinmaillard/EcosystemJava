package com.example.eco.MODEL;

public class LifeObject {
    private int urgeToReproduce = 70;
    private int feelUrgeToReproduce = 0;

    public LifeObject(){}

    public void setFeelUrgeToReproduce(int feelUrgeToReproduce) {
        this.feelUrgeToReproduce = feelUrgeToReproduce;
    }

    public int getFeelUrgeToReproduce() {
        return feelUrgeToReproduce;
    }

    public boolean ableToReproduce(){return (feelUrgeToReproduce>=urgeToReproduce);}
}

package com.example.eco;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    HelloController game;

    public GameLoop(HelloController g){this.game=g;}

    @Override
    public void handle(long l){
        game.update();
    }
}

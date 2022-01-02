package com.example.eco;

import com.example.eco.MODEL.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class HelloController {
    @FXML
    Pane pane;

    private int heightSize = 800;
    private int widthSize = 1200;
    private int columns = 60;
    private int rows = 40;
    private int squareWidth = widthSize / columns;
    private int squareHeight = heightSize / rows;
    private int radiusCircle = 8;
    public transient GameLoop gameLoop= new GameLoop(this);;

    private ArrayList<Plant> plants = new ArrayList<>();;
    private ArrayList<Hostile> hostiles = new ArrayList<>();
    private ArrayList<Passive> passives = new ArrayList<>();
    private ArrayList<Poop> poops = new ArrayList<>();
    private ArrayList<Meat> meats = new ArrayList<>();


    public void initialize(){
        for (int i = 0; i < widthSize; i +=squareWidth){
            for (int j = 0; j < heightSize; j +=squareHeight){
                Rectangle rect = new Rectangle( i, j, squareWidth, squareHeight);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                pane.getChildren().add(rect);
            }
        }

        startLifeGenerator(10 , 6, 8);
        gameLoop.start();

    }

    public void startLifeGenerator(int plantsNumber, int hostilesNumber, int passivesNumber){

        //add int random position plants to the pane
        for (int i = 0; i < plantsNumber; i++){
            Circle c = new Circle();
            c.setFill(Color.GREEN);
            c.setStroke(Color.BLACK);
            int x = squareWidth / 2 + squareWidth * (int)(Math.random() * columns);
            int y = squareHeight / 2 + squareHeight * (int)(Math.random() * rows);
            Plant p = new Plant(100, 60, 80, 40, c, radiusCircle, x, y);
            plants.add(p);
            pane.getChildren().add(c);
            p.draw();
        }

        //add int random position hostile to the pane
        for (int i = 0; i < hostilesNumber; i++){
            Circle c = new Circle();
            c.setFill(Color.RED);
            c.setStroke(Color.BLACK);
            double x = squareWidth / 2 + squareWidth * (int)(Math.random() * columns);
            double y = squareHeight / 2 + squareHeight * (int)(Math.random() * rows);
            double moveSpeedX = Math.random();
            double moveSpeedY = Math.random();
            int gender = (int)Math.random();
            int timeMove = (int)(Math.random() * 200);
            int timeRest = (int)(Math.random() * 500);
            Hostile h = new Hostile(100, 100, 40, 40, c, radiusCircle, x, y, gender, moveSpeedX, moveSpeedY, timeMove, timeRest);
            hostiles.add(h);
            pane.getChildren().add(c);
            h.draw();
        }

        //add int random position passives to the pane
        for (int i = 0; i < passivesNumber; i++){
            Circle c = new Circle();
            c.setFill(Color.BLUE);
            c.setStroke(Color.BLACK);
            double x = squareWidth / 2 + squareWidth * (int)(Math.random() * columns);
            double y = squareHeight / 2 + squareHeight * (int)(Math.random() * rows);
            double moveSpeedX = Math.random();
            double moveSpeedY = Math.random();
            int gender = (int)Math.random();
            int timeMove = (int)(Math.random() * 200);
            int timeRest = (int)(Math.random() * 500);
            Passive pas = new Passive(100, 100, 40, 40, c, radiusCircle, x, y, gender, moveSpeedX, moveSpeedY, timeMove, timeRest);
            passives.add(pas);
            pane.getChildren().add(c);
            pas.draw();
        }
    }

    //check passive and hostile position and changer their states
    public void update(){

        for (Hostile hostile : hostiles){
            if(hostile.getSatiety()==0){ //if death by hunger
                createMeat(hostile.getX(),hostile.getY()); //create meat after death
                pane.getChildren().remove(hostile.getCircle()); //remove from pane
                hostiles.remove(hostile); // remove from arraylist
            }
            else if(hostile.getSatiety()>=60) {move(hostile);}
            else if(hostile.isSearchFood()){
                searchPassive(hostile);
            }
        }

        for (Passive passive : passives){
            if(passive.getSatiety()==0 || passive.getHealth()==0){ //if death by hunger or by bleed
                createMeat(passive.getX(),passive.getY()); //create meat after death
                pane.getChildren().remove(passive.getCircle()); //remove from pane
                passives.remove(passive); // remove from arraylist
            }
            else if(passive.getSatiety()>=60) {move(passive);}

            else if(passive.isSearchFood()){
                searchPlant(passive);
            }
        }

        for (Plant plant : plants){
            if(plant.getHealth()==0){
                pane.getChildren().remove(plant.getCircle()); //remove from pane
                plants.remove(plant); // remove from arraylist
            }
            if(plant.getHealth()>0 && plant.getSatiety()>50) { plantSeeding(plant); }
            else{searchPoop(plant);}
        }
    }

    public void move(Animal animal){
        int timeM = animal.getTimeMove();
        int timeR = animal.getTimeRest();
        int rnd = (int)(Math.random()*10);
        if(animal.getMoveSpeedX()!=0 || animal.getMoveSpeedY()!=0){ //check if animal is moving
            if(animal.getX()>(double)(widthSize-radiusCircle/2) || animal.getX()<(double)(radiusCircle/2)){
                animal.setMoveSpeedX(-animal.getMoveSpeedX());
            }
            if(animal.getY()>(double)(heightSize-radiusCircle/2) || animal.getY()<(double)(radiusCircle/2)){
                animal.setMoveSpeedY(-animal.getMoveSpeedY());
            }
            animal.setX(animal.getX()+animal.getMoveSpeedX());
            animal.setY(animal.getY()+animal.getMoveSpeedY());
            animal.draw();
            animal.setTimeMove(timeM);
            if(animal.getTimeMove()==0){
                animal.setMoveSpeedX(0);
                animal.setMoveSpeedY(0);
                animal.setTimeRest((int)(Math.random() * 500));
            }
        }
        if(timeR>0 && animal.getMoveSpeedX()==0 && animal.getMoveSpeedY()==0){ //check if animal is resting
            animal.setTimeRest(timeR);
            if(animal.getTimeRest()==0){
                if(rnd <= 5){
                    animal.setMoveSpeedX(-Math.random());
                    animal.setMoveSpeedY(-Math.random());
                }
                else{
                    animal.setMoveSpeedX(Math.random());
                    animal.setMoveSpeedY(Math.random());
                }
                createPoop(animal.getX(), animal.getY());
                animal.setSatiety(animal.getSatiety()-5); //remove satiety each time a poop is created
                animal.setTimeMove((int)(Math.random() * 200));

            }
        }
    }

    public void createPoop(double x, double y){
        double giveSatiety = 5;
        Circle c = new Circle();
        c.setFill(Color.BROWN);
        c.setStroke(Color.BLACK);
        int radiusCircle = 4;
        Poop poo = new Poop(giveSatiety, c, radiusCircle, x, y);
        poops.add(poo);
        pane.getChildren().add(c);
        poo.draw();
    }

    public void createMeat(double x, double y){
        double giveSatiety = 5;
        Circle c = new Circle();
        c.setFill(Color.PURPLE);
        c.setStroke(Color.BLACK);
        int radiusCircle = 4;
        Meat rottenFlesh = new Meat(giveSatiety, c, radiusCircle, x, y);
        meats.add(rottenFlesh);
        pane.getChildren().add(c);
        rottenFlesh.draw();
    }


    public void plantSeeding(Plant plant){
        if (plant.getSatiety()>50){
            Random rnd = new Random();
            plant.setSatiety(plant.getSatiety()-50); //remove satiety for creating a new plant
            Circle c = new Circle();
            c.setFill(Color.GREEN);
            c.setStroke(Color.BLACK);
            int x = rnd.nextInt(2*plant.getSeedRadius()) + (int)(plant.getX() - plant.getSeedRadius());
            int y = rnd.nextInt(2*plant.getSeedRadius()) + (int)(plant.getY() - plant.getSeedRadius());
            Plant p = new Plant(100, 5, 80, 40, c, radiusCircle, x, y);
            plants.add(p);
            pane.getChildren().add(c);
            p.draw();
        }

    }

    public void searchPoop(Plant plant){
        double plantPosX = plant.getX();
        double plantPosY = plant.getY();
        for (Poop poo : poops){
            double poopPosX = poo.getX();
            double poopPosY = poo.getY();
            if((int)Math.sqrt( ((plantPosX-poopPosX)*(plantPosX-poopPosX)) + ((plantPosY-poopPosY)*(plantPosY-poopPosY)) )<=plant.getRootRadius()){ // check if the poop is in the range of the roots
                pane.getChildren().remove(poo.getCircle()); //remove from pane
                poops.remove(poo); // remove from arraylist
                plant.setSatiety(plant.getSatiety()+5); //add 5 satiety to the plant after the use of the poop
            }
        }
    }

    public void searchPlant(Animal animal){
        double animalPosX = animal.getX();
        double animalPosY = animal.getY();
        for (Plant plant : plants){
            double plantPosX = plant.getX();
            double plantPosY = plant.getY();
            int position = (int)Math.sqrt( ((plantPosX-animalPosX)*(plantPosX-animalPosX)) + ((plantPosY-animalPosY)*(plantPosY-animalPosY)));
            if( position <= animal.getVisionRadius() ){
                System.out.println("test2");
                animal.setMoveSpeedX((plantPosX-animalPosX)/100);
                animal.setMoveSpeedY((plantPosY-animalPosY)/100);
                if(position<=animal.getFeedRadius()){
                    System.out.println("test");
                    animal.setMoveSpeedX(0);
                    animal.setMoveSpeedY(0);
                    plant.setHealth(plant.getHealth()-20); //remove 20 lifePoint of the plant
                    animal.setSatiety(animal.getSatiety()+20); // give 20 satiety to the animal
                }
            }
            else{
                animal.setMoveSpeedX(1);
                animal.setMoveSpeedY(1);
                animal.setTimeMove(50);
                move(animal);
            }
        }
    }

    public void searchPassive(Hostile animal) {
        double animalPosX = animal.getX();
        double animalPosY = animal.getY();
        for (Passive passive : passives) {
            double passivePosX = passive.getX();
            double passivePosY = passive.getY();
            int position = (int)Math.sqrt( ((passivePosX-animalPosX)*(passivePosX-animalPosX)) + ((passivePosY-animalPosY)*(passivePosY-animalPosY)));
            if( position <= animal.getVisionRadius() ){
                System.out.println("test2");
                animal.setMoveSpeedX((passivePosX-animalPosX)/100);
                animal.setMoveSpeedY((passivePosY-animalPosY)/100);
                if(position<=animal.getFeedRadius()){
                    animal.setMoveSpeedX(0);
                    animal.setMoveSpeedY(0);
                    passive.setHealth(passive.getHealth()-20); //attack and remove 20 lifePoint of the passive
                }
            }
            else{
                animal.setMoveSpeedX(1);
                animal.setMoveSpeedY(1);
                animal.setTimeMove(50);
                move(animal);
            }
        }
    }

}
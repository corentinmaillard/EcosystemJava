# EcosystemJava
Ecosystem's simulation using javafx
Created by Maillard Corentin n°21306
# Framework
To launch it you have to start HelloApplication
![image](https://user-images.githubusercontent.com/33122169/147889201-84a6608c-0d43-44ae-ad1f-25419a259ca7.png)
## Entities in the simulation
1) The red circle are the hostile mob (carnivorous)
2) The blue circle are the passive mob (herbivorous)
3) The green circle are the plant
4) The brown circle are poop
5) The purple circle are meat

# Class diagram
![image](https://github.com/corentinmaillard/EcosystemJava/blob/main/Diagram/EcosystemClassDiagram.pdf)
![image](Diagram/EcosystemClassDiagram.png)
# Sequence diagram
# SOLID principles
## Liskov substitution principle
Let q(x) be a property provable about objects of x of type T.
Then q(y) should be provable for objects y of type S
where S is a subtype of T.

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

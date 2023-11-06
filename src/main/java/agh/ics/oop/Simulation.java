package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<MoveDirection> directions;
    public Simulation(LinkedList<MoveDirection> directions, LinkedList<Vector2d> startPositions){
        this.directions = directions;
        for(Vector2d position : startPositions){
            Animal currentAnimal = new Animal(position.getX(), position.getY());
            this.animals.add(currentAnimal);
        }
    }
    public LinkedList<Animal> getAnimals(){ return this.animals; }

    public void run(){
        int index = 0;
        for(MoveDirection direction : directions){
            int animalId = index % this.animals.size();
            Animal currentAnimal = animals.remove();
            this.animals.add(currentAnimal);
            currentAnimal.move(direction);
            System.out.println("Zwierzę " + animalId + " : " + currentAnimal.getPosition().toString());
            index++;
        }

    }
}

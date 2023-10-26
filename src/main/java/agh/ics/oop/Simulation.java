package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    public Simulation(List<MoveDirection> directions, List<Vector2d> startPositions){
        this.directions = directions;
        for(Vector2d position : startPositions){
            Animal currentAnimal = new Animal(position.getX(), position.getY());
            this.animals.add(currentAnimal);
        }
    }
    public List<Animal> getAnimals(){ return this.animals; }

    public void run(){
        int index = 0;
        for(MoveDirection direction : directions){
            int animalId = index % this.animals.size();
            Animal currentAnimal = animals.get(animalId);
            currentAnimal.move(direction);
            System.out.println("Zwierzę " + animalId + " : " + currentAnimal.getPosition().toString());
            index++;
        }

    }
}

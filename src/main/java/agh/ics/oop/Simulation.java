package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<MoveDirection> directions;
    private WorldMap map;

    public Simulation(LinkedList<MoveDirection> directions, LinkedList<Vector2d> startPositions, WorldMap map){
        this.directions = directions;
        this.map = map;
        for(Vector2d position : startPositions){
            Animal currentAnimal = new Animal(position.getX(), position.getY());
            animals.add(currentAnimal);
        }
    }
    public LinkedList<Animal> getAnimals(){ return animals; }

    public void run(){
        int index = 0;
        for(Animal animal : animals){
            map.place(animal);
        }

        for(MoveDirection direction : directions){
            int animalId = index % animals.size();
            Animal currentAnimal = animals.remove();
            animals.add(currentAnimal);
            map.move(currentAnimal, direction);
            System.out.println(map.toString());
            index++;
        }

    }
}

package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.WorldMap;
import agh.ics.oop.model.world_elements.Animal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Simulation {
    private List<Animal> animals = new ArrayList<>();
    private List<MoveDirection> directions;
    private WorldMap<Animal, Vector2d> map;

    public Simulation(List<MoveDirection> directions, List<Vector2d> startPositions, WorldMap<Animal, Vector2d> map){
        this.directions = directions;
        this.map = map;
        for(Vector2d position : startPositions){
            Animal currentAnimal = new Animal(position.getX(), position.getY());
            animals.add(currentAnimal);
        }
    }
    public List<Animal> getAnimals(){ return animals; }

    public void run(){
        int index = 0;
        for(Animal animal : animals){
            map.place(animal);
        }

        for(MoveDirection direction : directions){
            int animalId = index % animals.size();
            Animal currentAnimal = animals.get(animalId);
            map.move(currentAnimal, direction);
            System.out.println(map.toString());
            index++;
        }

    }
}

package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.WorldMap;
import agh.ics.oop.model.world_elements.Animal;
import agh.ics.oop.model.world_elements.WorldElement;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> directions;
    private final WorldMap<WorldElement, Vector2d> map;

    public Simulation(List<MoveDirection> directions, List<Vector2d> startPositions, WorldMap<WorldElement, Vector2d> map){
        this.directions = directions;
        this.map = map;
        populate(startPositions);
    }

    private void populate(List<Vector2d> positions){
        for(Vector2d position : positions){
            Animal currentAnimal = new Animal(position);
            animals.add(currentAnimal);
            map.place(currentAnimal);
        }
    }

    public List<Animal> getAnimals(){ return animals; }

    public void run(){
        int index = 0;
        for(MoveDirection direction : directions){
            int animalId = index % animals.size();
            Animal currentAnimal = animals.get(animalId);
            map.move(currentAnimal, direction);
            System.out.println(map);
            index++;
        }
    }
}

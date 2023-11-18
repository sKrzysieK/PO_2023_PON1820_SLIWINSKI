package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.exceptions.PositionOutOfBoundariesException;
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
            addAnimalToSimulation(currentAnimal, position);
        }
    }

    private void addAnimalToSimulation(Animal animal, Vector2d position){
        try{
            if(!map.canMoveTo(position) && !map.isOccupied(position)) throw new PositionOutOfBoundariesException(position);
            map.place(animal);
            animals.add(animal);
        }catch( PositionOutOfBoundariesException e){
            System.out.printf(e.getMessage());
        }catch( PositionAlreadyOccupiedException e){
            System.out.println(e.getMessage());
        }
    }

    public void run(){
        if(animals.isEmpty()) return;
        int index = 0;
        for(MoveDirection direction : directions){
            int animalId = index % animals.size();
            Animal currentAnimal = animals.get(animalId);
            map.move(currentAnimal, direction);
            index++;
        }
    }

    public List<Animal> getAnimals(){ return animals; }

}

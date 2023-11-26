package agh.ics.oop.model.maps;

import agh.ics.oop.model.*;
import agh.ics.oop.model.world_elements.Animal;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private static final Vector2d MAP_LOWER_LEFT = new Vector2d(0, 0);
    private static Vector2d MAP_UPPER_RIGHT;
    private final int height;
    private final int width;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        MAP_UPPER_RIGHT = new Vector2d(width, height);
    }

    @Override
    public boolean place(Animal animal){
        Vector2d newAnimalPosition = animal.getPosition();
        if(!canMoveTo(newAnimalPosition)) return false;
        animals.put(newAnimalPosition, animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        Vector2d startPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d endPosition = animal.getPosition();
        System.out.println("Start pos: " + startPosition + ", end pos: " + endPosition + ", orientation: " + animal.getOrientation());
        animals.remove(startPosition);
        animals.put(endPosition, animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position)
                && position.follows(MAP_LOWER_LEFT)
                && position.precedes(MAP_UPPER_RIGHT);

    }

    @Override
    public boolean isOccupied(Vector2d position){
        for(Vector2d key : animals.keySet()){
            if(key.equals(position)) return true;
        }
        return false;
    }

    @Override
    public Animal objectAt(Vector2d position){
        return animals.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(MAP_LOWER_LEFT, MAP_UPPER_RIGHT);
    }

}
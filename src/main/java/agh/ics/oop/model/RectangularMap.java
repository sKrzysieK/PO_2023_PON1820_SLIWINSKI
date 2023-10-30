package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{
    private int height;
    private int width;
    private Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean place(Animal animal){
        Vector2d newAnimalPosition = animal.getPosition();
        if(!this.isOccupied(newAnimalPosition)){
            animals.put(newAnimalPosition, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        animal.move(direction);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position)
                && position.follows(new Vector2d(0,0))
                && position.precedes(new Vector2d(this.width, this.height));

    }

    @Override
    public boolean isOccupied(Vector2d position){
        for(Vector2d key : this.animals.keySet()){
            if(key.equals(position)) return true;
        }
        return false;
    }

    @Override
    public Animal objectAt(Vector2d position){
        return this.animals.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0,0), new Vector2d(this.width,this.height));
    }

}
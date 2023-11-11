package agh.ics.oop.model.maps;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.world_elements.Animal;
import agh.ics.oop.model.world_elements.Grass;
import agh.ics.oop.model.world_elements.WorldElement;

import java.util.HashMap;
import java.util.Map;

public class GrassField implements WorldMap<WorldElement<Vector2d>, Vector2d> {
    private final int grassCount;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount){
        this.grassCount = grassCount;
    }

    @Override
    public boolean place(WorldElement<Vector2d> obj){
        return true;
    }

    @Override
    public void move(WorldElement<Vector2d> obj, MoveDirection direction){
        return;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return true;
    }

    @Override
    public WorldElement<Vector2d> objectAt(Vector2d position){
        return  new Animal();
    }
}

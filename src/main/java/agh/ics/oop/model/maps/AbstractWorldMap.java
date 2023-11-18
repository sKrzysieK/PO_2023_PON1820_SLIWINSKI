package agh.ics.oop.model.maps;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.MapVisualizer;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.world_elements.Animal;
import agh.ics.oop.model.world_elements.WorldElement;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Boundary mapBoundary;

    @Override
    public void place(WorldElement obj) throws PositionAlreadyOccupiedException{
        if(obj instanceof Animal animal) {
            Vector2d newPosition = obj.getPosition();
            if (!this.canMoveTo(newPosition)) throw new PositionAlreadyOccupiedException(newPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public void move(WorldElement obj, MoveDirection direction){
        if(!(obj instanceof Animal animal)) return;
        Vector2d startPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d endPosition = animal.getPosition();
        System.out.println("Start pos: " + startPosition + ", end pos: " + endPosition + ", orientation: " + animal.getOrientation());
        animals.remove(startPosition);
        animals.put(endPosition, animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        mapBoundary = getCurrentBounds();
        return visualizer.draw(mapBoundary.lowerLeft(), mapBoundary.upperRight());
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        return new HashMap<>(animals);
    }

}

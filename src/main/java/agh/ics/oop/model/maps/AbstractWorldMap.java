package agh.ics.oop.model.maps;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.MapVisualizer;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.world_elements.Animal;
import agh.ics.oop.model.world_elements.WorldElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements WorldMap<WorldElement, Vector2d> {
    protected List<MapChangeListener> listeners = new ArrayList<>();
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Boundary mapBoundary;

    @Override
    public void place(WorldElement obj) throws PositionAlreadyOccupiedException{
        if(obj instanceof Animal animal) {
            Vector2d newPosition = obj.getPosition();
            if (!this.canMoveTo(newPosition)) throw new PositionAlreadyOccupiedException(newPosition);
            animals.put(newPosition, animal);
            mapChanged(createAnimalPlaceMessage(newPosition));
        }
    }

    @Override
    public void move(WorldElement obj, MoveDirection direction){
        if(!(obj instanceof Animal animal)) return;
        Vector2d startPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d endPosition = animal.getPosition();
        animals.remove(startPosition);
        animals.put(endPosition, animal);
        mapChanged(createAnimalMoveMessage(startPosition, endPosition, animal));
    }

    public void mapChanged(String message){
        for(MapChangeListener listener : listeners){
            listener.mapChanged(this, message);
        }
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        mapBoundary = getCurrentBounds();
        return visualizer.draw(mapBoundary.lowerLeft(), mapBoundary.upperRight());
    }

    private String createAnimalMoveMessage(Vector2d start, Vector2d end, Animal animal){
        return "Start pos: " + start + ", end pos: " + end + ", orientation: " + animal.getOrientation();
    }

    private String createAnimalPlaceMessage(Vector2d position){
        return "Placed animal on position " + position;
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        return new HashMap<>(animals);
    }

    public void addListener(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MapChangeListener listener){
        listeners.remove(listener);
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

}

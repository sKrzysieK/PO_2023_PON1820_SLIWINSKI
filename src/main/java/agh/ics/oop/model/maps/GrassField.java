package agh.ics.oop.model.maps;

import agh.ics.oop.model.MapVisualizer;
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
        this.generateGrass();
    }

    private void generateGrass(){
        int grassLeftCounter = grassCount;
        int sqrt10N = (int) Math.sqrt(10 * grassCount);
        Vector2d grassUpperRight = new Vector2d(sqrt10N, sqrt10N);

        while(grassLeftCounter > 0){
            int randomX = (int) (Math.random() * grassUpperRight.getX());
            int randomY = (int) (Math.random() * grassUpperRight.getY());
            Vector2d newRandomPosition = new Vector2d(randomX, randomY);
            if(!this.hasGrass(newRandomPosition)){
                this.place(new Grass(newRandomPosition));
                grassLeftCounter--;
            }
        }
    }

    @Override
    public boolean place(WorldElement<Vector2d> obj){
        switch(obj.getClass().getSimpleName()){
            case "Animal" -> {
                Animal animal = (Animal) obj;
                Vector2d newPosition = obj.getPosition();
                if(this.canMoveTo(newPosition)) {
                    animals.put(newPosition, animal);
                    return true;
                }
            }
            case "Grass" -> {
                Grass grass = (Grass) obj;
                Vector2d newPosition = grass.getPosition();
                if(!this.hasGrass(newPosition)){
                    grasses.put(newPosition, grass);
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void move(WorldElement<Vector2d> obj, MoveDirection direction){
        if(!obj.getClass().getSimpleName().equals("Animal")) return;
        Animal animal = (Animal) obj;
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
                && position.follows(new Vector2d(0,0));
    }

    @Override
    public boolean isOccupied(Vector2d position){
        for(Vector2d key : animals.keySet()){
            if(key.equals(position)) return true;
        }
        return false;
    }

    private boolean hasGrass(Vector2d position){
        for(Vector2d key : grasses.keySet()){
            if(key.equals(position)) return true;
        }
        return false;
    }

    @Override
    public WorldElement<Vector2d> objectAt(Vector2d position){
        Animal animalAt = animals.get(position);
        Grass grassAt = grasses.get(position);
        return (animalAt != null ? animalAt : grassAt);
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d upperRight = new Vector2d(0, 0);
        for(Vector2d key : animals.keySet()){
            if(key.follows(upperRight)) upperRight = key;
        }
        for(Vector2d key : grasses.keySet()){
            if(key.follows(upperRight)) upperRight = key;
        }

        return visualizer.draw(new Vector2d(0,0), upperRight);
    }
}

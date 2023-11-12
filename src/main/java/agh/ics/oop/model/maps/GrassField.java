package agh.ics.oop.model.maps;

import agh.ics.oop.model.MapVisualizer;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.world_elements.Grass;
import agh.ics.oop.model.world_elements.WorldElement;

import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private final int grassCount;
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

    private void calcExtremes(){
        Vector2d currUpperRight = new Vector2d(0, 0);
        Vector2d currLowerLeft = new Vector2d(0, 0);
        for(Vector2d key : animals.keySet()){
            currUpperRight = key.upperRight(currUpperRight);
            currLowerLeft = key.lowerLeft(currLowerLeft);
        }
        for(Vector2d key : grasses.keySet()){
            currUpperRight = key.upperRight(currUpperRight);
            currLowerLeft = key.lowerLeft(currLowerLeft);
        }
        mapLowerLeft = currLowerLeft;
        mapUpperRight = currUpperRight;
    }

    @Override
    public boolean place(WorldElement obj){
        if(super.place(obj)) return true;
        if(obj.getClass().getSimpleName().equals("Grass")){
            Grass grass = (Grass) obj;
            Vector2d newPosition = grass.getPosition();
            if(!this.hasGrass(newPosition)){
                grasses.put(newPosition, grass);
                return true;
            }
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
    public WorldElement objectAt(Vector2d position){
        WorldElement animalAt = super.objectAt(position);
        Grass grassAt = grasses.get(position);
        return (animalAt != null ? animalAt : grassAt);
    }

    @Override
    public String toString(){
        this.calcExtremes();
        return super.toString();
    }
}

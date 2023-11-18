package agh.ics.oop.model.maps;

import agh.ics.oop.RandomPositionGenerator;
import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.world_elements.Grass;
import agh.ics.oop.model.world_elements.WorldElement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrassField extends AbstractWorldMap {
    private final static int MAX_RANGE_MODIFIER = 10;
    private final int grassCount;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount){
        this.grassCount = grassCount;
        this.generateGrass();
    }

    private void generateGrass(){
        int maxRange = calcMaxRange();
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxRange, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            try{
                place(new Grass(grassPosition));
            } catch (PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private int calcMaxRange(){
        return (int) Math.sqrt(MAX_RANGE_MODIFIER * grassCount);
    }

    @Override
    public Boundary getCurrentBounds(){
        HashSet<Vector2d> keySet = new HashSet<>(animals.keySet());
        keySet.addAll(grasses.keySet());
        Vector2d currLowerLeft = new Vector2d(0,0);
        Vector2d currUpperRight = new Vector2d(0,0);
        for(Vector2d key : keySet){
            currLowerLeft = key.lowerLeft(currLowerLeft);
            currUpperRight = key.upperRight(currUpperRight);
        }
        return new Boundary(currLowerLeft, currUpperRight);
    }

    @Override
    public void place(WorldElement obj) throws PositionAlreadyOccupiedException{
        super.place(obj);
        if(obj instanceof Grass grass){
            Vector2d newPosition = grass.getPosition();
            grasses.put(newPosition, grass);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position){
        WorldElement animalAt = super.objectAt(position);
        Grass grassAt = grasses.get(position);
        return (animalAt != null ? animalAt : grassAt);
    }

    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> elements = new HashMap<>();
        elements.putAll(grasses);
        elements.putAll(super.getElements());
        return elements;
    }


}

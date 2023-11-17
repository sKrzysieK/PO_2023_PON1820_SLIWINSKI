package agh.ics.oop.model.maps;

import agh.ics.oop.model.Vector2d;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        mapUpperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position) && isWithinBoundaries(position);
    }

    private boolean isWithinBoundaries(Vector2d position){
        return position.follows(mapLowerLeft) && position.precedes(mapUpperRight);
    }

}
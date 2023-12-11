package agh.ics.oop.model.maps;

import agh.ics.oop.model.Boundary;
import agh.ics.oop.model.Vector2d;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;
    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position) && isWithinBoundaries(position);
    }

    @Override
    public Boundary getCurrentBounds(){
        return new Boundary(new Vector2d(0,0), new Vector2d(width, height));
    }

    private boolean isWithinBoundaries(Vector2d position){
        return position.follows(getCurrentBounds().lowerLeft())
                && position.precedes(getCurrentBounds().upperRight());
    }

}
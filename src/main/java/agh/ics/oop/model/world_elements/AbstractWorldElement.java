package agh.ics.oop.model.world_elements;

import agh.ics.oop.model.Vector2d;

abstract class AbstractWorldElement implements WorldElement {
    protected Vector2d position;

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public abstract String toString();
}

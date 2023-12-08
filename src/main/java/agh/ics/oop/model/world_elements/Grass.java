package agh.ics.oop.model.world_elements;

import agh.ics.oop.model.Vector2d;

public class Grass extends AbstractWorldElement {
    public Grass(Vector2d position){
        this.position = position;
    }

    @Override
    public String toString(){
        return "*";
    }
}

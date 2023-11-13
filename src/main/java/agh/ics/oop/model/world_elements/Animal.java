package agh.ics.oop.model.world_elements;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.MoveValidator;
import agh.ics.oop.model.Vector2d;

public class Animal implements WorldElement{
    private final static int DEFAULT_START_POS = 2;
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal(){
        this(DEFAULT_START_POS, DEFAULT_START_POS);
    }

    public Animal(int x, int y){
        position = new Vector2d(x, y);
    }

    public void move(MoveDirection direction, MoveValidator<Vector2d> validator){
        switch(direction){
            case FORWARD -> {
                Vector2d nextPosition = position.add(orientation.toUnitVector());
                if(validator.canMoveTo(nextPosition)) position = nextPosition;
            }
            case BACKWARD -> {
                Vector2d nextPosition = position.add(orientation.toUnitVector().opposite());
                if(validator.canMoveTo(nextPosition)) position = nextPosition;
            }
            case RIGHT, LEFT -> orientation = this.rotate(direction);
        }
    }

    private MapDirection rotate(MoveDirection direction){
        return switch (direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            default -> orientation;
        };
    }

    @Override
    public Vector2d getPosition(){
        return position;
    }

    public MapDirection getOrientation(){
        return orientation;
    }


    @Override
    public String toString(){
        return switch (orientation){
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }
}


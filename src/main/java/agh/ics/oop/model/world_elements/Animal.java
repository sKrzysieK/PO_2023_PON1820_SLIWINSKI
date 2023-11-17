package agh.ics.oop.model.world_elements;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.MoveValidator;
import agh.ics.oop.model.Vector2d;

public class Animal extends AbstractWorldElement{
    private final static Vector2d DEFAULT_START_POS = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;

    public Animal(){
        this(DEFAULT_START_POS);
    }

    public Animal(Vector2d startPos){
        position = startPos;
    }

    public void move(MoveDirection direction, MoveValidator<Vector2d> validator){
        switch(direction){
            case FORWARD, BACKWARD -> moveIfPossible(direction, validator);
            case RIGHT, LEFT -> orientation = this.rotate(direction);
        }
    }

    private void moveIfPossible(MoveDirection direction, MoveValidator<Vector2d> validator){
        Vector2d moveVector = direction.equals(MoveDirection.FORWARD) ? orientation.toUnitVector() : orientation.toUnitVector().opposite();
        Vector2d nextPosition = position.add(moveVector);
        if(validator.canMoveTo(nextPosition)) position = nextPosition;
    }

    private MapDirection rotate(MoveDirection direction){
        return switch (direction){
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            default -> orientation;
        };
    }

    public MapDirection getOrientation(){
        return orientation;
    }

    @Override
    public String toString(){
        return String.valueOf(orientation.toString().charAt(0));
    }
}


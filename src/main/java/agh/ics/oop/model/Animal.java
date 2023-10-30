package agh.ics.oop.model;

import agh.ics.oop.World;

public class Animal {

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;


    public Animal(){
        position = new Vector2d(2,2);
    }
    public Animal(int x, int y){
        position = new Vector2d(x, y);
    }

    public Vector2d getPosition(){
        return position;
    }
    public MapDirection getOrientation(){
        return orientation;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
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

    public void move(MoveDirection direction, MoveValidator validator){
        MapDirection startOrientation = orientation;

        // change orientation
        switch(direction){
            case RIGHT -> {
                orientation = orientation.next();
                return;
            }
            case LEFT -> {
                orientation = orientation.previous();
                return;
            }
        }

        // move
        int multiplier = direction.equals(MoveDirection.FORWARD) ? 1 : -1;
        Vector2d nextPosition = switch(orientation){
            case EAST -> position.add(new Vector2d(multiplier,0));
            case WEST -> position.add(new Vector2d(-multiplier,0));
            case NORTH -> position.add(new Vector2d(0,multiplier));
            case SOUTH -> position.add(new Vector2d(0,-multiplier));
        };


        if(!validator.canMoveTo(nextPosition)){
            orientation = startOrientation;
            return;
        }

        position = nextPosition;

    }
}


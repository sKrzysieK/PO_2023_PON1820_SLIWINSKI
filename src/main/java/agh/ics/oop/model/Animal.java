package agh.ics.oop.model;

import agh.ics.oop.World;

public class Animal {

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;


    public Animal(){
        this.position = new Vector2d(2,2);
    }
    public Animal(int x, int y){
        this.position = new Vector2d(x, y);
    }

    public Vector2d getPosition(){
        return this.position;
    }
    public MapDirection getOrientation(){
        return this.orientation;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    @Override
    public String toString(){
        return "Current Position: " +
                this.position.toString() +
                ", Current Orientation: " +
                this.orientation.toString();
    }

    public void move(MoveDirection direction){
        MapDirection startOrientation = this.orientation;
        Vector2d mapStartPoint = new Vector2d(0,0);
        Vector2d mapEndPoint = new Vector2d(World.MAP_SIZE, World.MAP_SIZE);

        // change orientation
        switch(direction){
            case RIGHT -> {
                this.orientation = this.orientation.next();
                return;
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
                return;
            }
        }

        // move
        int multiplier = direction.equals(MoveDirection.FORWARD) ? 1 : -1;
        Vector2d nextPosition = switch(this.orientation){
            case EAST -> this.position.add(new Vector2d(multiplier,0));
            case WEST -> this.position.add(new Vector2d(-multiplier,0));
            case NORTH -> this.position.add(new Vector2d(0,multiplier));
            case SOUTH -> this.position.add(new Vector2d(0,-multiplier));
        };


        if(!(nextPosition.follows(mapStartPoint) && nextPosition.precedes(mapEndPoint))){
            this.orientation = startOrientation;
            return;
        }

        this.position = nextPosition;

    }
}


package agh.ics.oop.model;

public class Animal {
    private final static int MAP_SIZE = 4;
    private MapDirection orientation;
    private Vector2d position;


    public Animal(){
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }
    public Animal(int x, int y){
        this.position = new Vector2d(x, y);
        this.orientation = MapDirection.NORTH;
    }

    public Vector2d getPosition(){
        return this.position;
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
        Vector2d mapEndPoint = new Vector2d(MAP_SIZE, MAP_SIZE);

        // change orientation
        switch(direction){
            case BACKWARD -> this.orientation = this.orientation.next().next();
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }

        // move
        Vector2d nextPosition = switch(this.orientation){
            case EAST -> this.position.add(new Vector2d(1,0));
            case WEST -> this.position.add(new Vector2d(-1,0));
            case NORTH -> this.position.add(new Vector2d(0,1));
            case SOUTH -> this.position.add(new Vector2d(0,-1));
        };

        if(!(nextPosition.follows(mapStartPoint) && nextPosition.precedes(mapEndPoint))){
            this.orientation = startOrientation;
            return;
        }

        this.position = nextPosition;

    }
}


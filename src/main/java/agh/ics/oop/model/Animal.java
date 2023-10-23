package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
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
        // change orientation
        switch(direction){
            case BACKWARD -> this.orientation = this.orientation.next().next();
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }

        // move
        switch(this.orientation){
            case NORTH -> {
                Vector2d nextPosition = this.position.add(new Vector2d(0, 1));
                if(nextPosition.getY() <= 4) this.position = nextPosition;
            }
            case SOUTH -> {
                Vector2d nextPosition = this.position.add(new Vector2d(0, -1));
                if(nextPosition.getY() >= 0) this.position = nextPosition;
            }
            case EAST -> {
                Vector2d nextPosition = this.position.add(new Vector2d(1, 0));
                if(nextPosition.getX() <= 4) this.position = nextPosition;
            }
            case WEST -> {
                Vector2d nextPosition = this.position.add(new Vector2d(-1, 0));
                if(nextPosition.getY() >= 0) this.position = nextPosition;
            }
        }

    }
}


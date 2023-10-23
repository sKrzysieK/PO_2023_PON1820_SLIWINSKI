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

}


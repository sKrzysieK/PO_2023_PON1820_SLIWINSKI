package agh.ics.oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String toString(){
        return "(%s, %s)".formatted(x, y);
    }

    public boolean precedes(Vector2d other){
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other){
        return x >= other.x && y >= other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(
                x > other.x ? x : other.x,
                y > other.y ? y : other.y
        );
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(
                x < other.x ? x : other.x,
                y < other.y ? y : other.y
        );
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2d)) return false;
        Vector2d that = (Vector2d) o;
        return x == that.x && y == that.y;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }


}

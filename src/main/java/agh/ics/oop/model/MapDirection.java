package agh.ics.oop.model;

public enum MapDirection {
    NORTH("Północ"),
    SOUTH("Południe"),
    WEST("Zachód"),
    EAST("Wschód");

    private final String str;

    MapDirection(String str){
        this.str=str;
    }

    public String toString(){
        return str;
    }

    public MapDirection next(){
        return switch(this){
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public MapDirection previous(){
        return switch(this){
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
        };
    }
}

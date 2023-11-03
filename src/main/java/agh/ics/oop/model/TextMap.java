package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldMap<String, Integer>{
    private int N;
    private Map<Integer, String> strings = new HashMap<>();

    public TextMap(){
        this.N = 0;
    }

    public int getLength(){
        return N;
    }

    @Override
    public boolean place(String str){
        this.strings.put(N, str);
        N++;
        return true;
    }

    @Override
    public void move(String str, MoveDirection direction){
        Integer strIndex = null;
        for(Integer key : strings.keySet()){
            if(strings.get(key).equals(str)) strIndex = key;
        }
        if(strIndex == null) return;
        switch(direction){
            case RIGHT -> {
                if(canMoveTo(strIndex + 1)) {
                    String other = strings.get(strIndex + 1);
                    strings.put(strIndex + 1, str);
                    strings.put(strIndex, other);
                }
            }
            case LEFT -> {
                if(canMoveTo(strIndex - 1)){
                    String other = strings.get(strIndex - 1);
                    strings.put(strIndex - 1, str);
                    strings.put(strIndex, other);
                }
            }
        }

    }

    @Override
    public boolean canMoveTo(Integer position){
        return position < N && position >= 0;
    }

    @Override
    public boolean isOccupied(Integer position){
        return false;
    }

    @Override
    public String objectAt(Integer position){
        return strings.get(position);
    }

    @Override
    public String toString(){
        String output = "";
        for(String value : strings.values()){
            output += "%s |".formatted(value);
        }
        return output;
    }

}

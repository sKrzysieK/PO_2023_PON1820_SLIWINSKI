package agh.ics.oop.model.maps;

import agh.ics.oop.model.MoveDirection;

import java.util.HashMap;
import java.util.Map;

public class TextMap implements WorldMap<String, Integer> {
    private int N;
    private final Map<Integer, String> strings = new HashMap<>();

    public TextMap(){
        this.N = 0;
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
            case RIGHT -> shift(str, strIndex, 1);
            case LEFT -> shift(str, strIndex, -1);
        }
    }

    private void shift(String str, int currStrIndex, int modifier){
        int nextStringIndex = currStrIndex + modifier;
        if(canMoveTo(nextStringIndex)){
            String other = strings.get(nextStringIndex);
            strings.put(nextStringIndex, str);
            strings.put(currStrIndex, other);
        }
    }

    @Override
    public boolean canMoveTo(Integer position){
        return position < N && position >= 0;
    }

    @Override
    public boolean isOccupied(Integer position){
        return position >=0 && position <= N;
    }

    public int getLength(){
        return N;
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

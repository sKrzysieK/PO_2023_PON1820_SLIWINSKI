package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static LinkedList<MoveDirection> parse(String args[]){
        LinkedList<MoveDirection> directions = new LinkedList<>();
        for(String arg: args){
            switch(arg){
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "l" -> directions.add(MoveDirection.LEFT);
                case "r" -> directions.add(MoveDirection.RIGHT);
            }
        }
        return directions;
    }
}

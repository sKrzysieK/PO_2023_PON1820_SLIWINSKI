package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String args[]){
        MoveDirection directions[] = new MoveDirection[args.length];
        int index = 0;
        for(String arg: args){
            switch(arg){
                case "f" -> directions[index] = MoveDirection.FORWARD;
                case "b" -> directions[index] = MoveDirection.BACKWARD;
                case "l" -> directions[index] = MoveDirection.LEFT;
                case "r" -> directions[index] = MoveDirection.RIGHT;
                default -> index--;
            }
            index++;
        }
        return Arrays.copyOfRange(directions, 0, index);
    }
}

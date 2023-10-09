package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String args[]){
        System.out.println("Start");
        run(OptionsParser.parse(args));
        System.out.println("Stop");
    }
    public static void run(MoveDirection directions[]){
//        System.out.println("Zwierzak idzie do przodu");
//        int index = 0;
//        for(String arg: args){
//            if(index < args.length - 1) System.out.print(arg + ", ");
//            else System.out.println(arg);
//            index++;
//        }
        for(MoveDirection direction: directions){
            String direction_text = switch(direction){
                case FORWARD -> "do przodu";
                case BACKWARD -> "do tyłu";
                case LEFT -> "w lewo";
                case RIGHT -> "w prawo";
                default -> "";
            };

            if(direction_text != "") System.out.println("Zwierzak idzie " + direction_text);

        }
    }
}

package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.LinkedList;
import java.util.List;

public class World {
    public final static int MAP_SIZE = 4;
    public static void main(String args[]){
        Animal animal1 = new Animal();
        System.out.println(animal1.toString());
        System.out.println(animal1.isAt(new Vector2d(2, -2)));
        System.out.println(animal1.isAt(new Vector2d(2,2)));

        LinkedList<MoveDirection> directions = OptionsParser.parse(args);
        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(2,2)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();
    }
    public static void run(LinkedList<MoveDirection> directions){
        for(MoveDirection direction: directions){
            String direction_text = switch(direction){
                case FORWARD -> "do przodu";
                case BACKWARD -> "do tyłu";
                case LEFT -> "w lewo";
                case RIGHT -> "w prawo";
            };

            System.out.println("Zwierzak idzie " + direction_text);

        }
    }
}

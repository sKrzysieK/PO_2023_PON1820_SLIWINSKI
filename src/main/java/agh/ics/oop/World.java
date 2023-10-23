package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String args[]){
        System.out.println("Start");
        run(OptionsParser.parse(args));
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection direction = MapDirection.SOUTH;
        System.out.println("This direction: " + direction.toString());
        MapDirection nextDirection = direction.next();
        MapDirection prevDirection = direction.previous();
        System.out.println("Next direction: " + nextDirection);
        System.out.println("Previous direction: " + prevDirection);
        System.out.println("To Unit Vector: ");
        System.out.println("This direction: " + direction.toUnitVector().toString());
        System.out.println("Next direction: " + nextDirection.toUnitVector().toString());
        System.out.println("Previous direction: " + prevDirection.toUnitVector().toString());

        Animal animal1 = new Animal();
        System.out.println(animal1.toString());
        System.out.println(animal1.isAt(new Vector2d(2, -2)));
        System.out.println(animal1.isAt(new Vector2d(2,2)));

    }
    public static void run(List<MoveDirection> directions){
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

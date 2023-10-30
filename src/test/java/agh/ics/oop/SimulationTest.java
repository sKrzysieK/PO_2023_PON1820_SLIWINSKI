package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void goAllForward(){
        String directionsArr[] = new String[World.MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(start, start)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(new Vector2d(start, World.MAP_SIZE), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void goAllRight(){
        String directionsArr[] = new String[World.MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        directionsArr[0] = "r";

        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(start,start)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(new Vector2d(World.MAP_SIZE, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.EAST);
    }

    @Test
    void goAllBackwards(){
        String directionsArr[] = new String[World.MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "b");

        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(start,start)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(new Vector2d(start, 0), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void goAllLeft(){
        String directionsArr[] = new String[World.MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        directionsArr[0] = "l";

        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(start,start)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(new Vector2d(0, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void turnAroundAndReturn(){
        String directionsArr[] = {"r", "r", "r", "r"};
        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 3;

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(new Vector2d(start,start)));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(new Vector2d(start, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void oneAnimalRandomMovement(){
        String directionsArr[] = {"f", "r", "r", "f", "r", "f", "r", "f", "f", "f", "f", "r", "f", "f", "f", "l", "f", "r", "r", "b", "r"};
        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPosition = new Vector2d(1, 2);
        Vector2d endPosition = new Vector2d(3, 4);

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(startPosition));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animal = simulation.getAnimals().remove();
        assertEquals(endPosition, animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void twoAnimalsRandomMovement(){
        String directionsArr[] =
                {"f", "l", "f", "f", "r", "f", "f", "l", "r", "b", "b", "b", "b", "l", "l", "l", "f", "f", "r", "r", "b", "r", "f"};
        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPositionAnimalOne = new Vector2d(0, 0);
        Vector2d startPositionAnimalTwo = new Vector2d(4, 0);
        Vector2d endPosition = new Vector2d(2, 3);

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(startPositionAnimalOne, startPositionAnimalTwo));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animalOne = simulation.getAnimals().remove();
        Animal animalTwo = simulation.getAnimals().remove();

        assertEquals(endPosition, animalOne.getPosition());
        assertEquals(endPosition, animalTwo.getPosition());

        assertEquals(animalOne.getOrientation(), MapDirection.SOUTH);
        assertEquals(animalTwo.getOrientation(), MapDirection.SOUTH);
    }

    @Test
    void fourAnimalsRandomMovement(){
        String directionsArr[] =
                       {"f", "r", "f", "b",
                        "r", "f", "l", "f",
                        "f", "l", "f", "f",
                        "f", "f", "l", "r",
                        "r", "l", "f", "f",
                        "f", "f", "f", "r",
                        "f", "r", "l", "r"
                       };
        LinkedList<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPositionAnimalOne = new Vector2d(0, 3);
        Vector2d startPositionAnimalTwo = new Vector2d(2, 1);
        Vector2d startPositionAnimalThree = new Vector2d(3, 3);
        Vector2d startPositionAnimalFour = new Vector2d(1, 0);
        Vector2d endPosition = new Vector2d(2, 2);

        LinkedList<Vector2d> positions = new LinkedList<>(List.of(startPositionAnimalOne, startPositionAnimalTwo, startPositionAnimalThree, startPositionAnimalFour));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        Animal animalOne = simulation.getAnimals().remove();
        Animal animalTwo = simulation.getAnimals().remove();
        Animal animalThree = simulation.getAnimals().remove();
        Animal animalFour = simulation.getAnimals().remove();

        assertEquals(endPosition, animalOne.getPosition());
        assertEquals(endPosition, animalTwo.getPosition());
        assertEquals(endPosition, animalThree.getPosition());
        assertEquals(endPosition, animalFour.getPosition());

        assertEquals(animalOne.getOrientation(), MapDirection.SOUTH);
        assertEquals(animalTwo.getOrientation(), MapDirection.NORTH);
        assertEquals(animalThree.getOrientation(), MapDirection.EAST);
        assertEquals(animalFour.getOrientation(), MapDirection.WEST);

    }

}
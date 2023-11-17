package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.RectangularMap;
import agh.ics.oop.model.world_elements.Animal;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    private final static int MAP_SIZE = 4;
    @Test
    void goAllForward(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = new String[MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        List<Vector2d> positions = List.of(new Vector2d(start, start));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(start, MAP_SIZE), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void goAllRight(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = new String[MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        directionsArr[0] = "r";

        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        List<Vector2d> positions = List.of(new Vector2d(start,start));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(MAP_SIZE, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.EAST);
    }

    @Test
    void goAllBackwards(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = new String[MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "b");

        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        List<Vector2d> positions = List.of(new Vector2d(start,start));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(start, 0), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void goAllLeft(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = new String[MAP_SIZE + 10] ;
        Arrays.fill(directionsArr, "f");
        directionsArr[0] = "l";

        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 2;

        List<Vector2d> positions = List.of(new Vector2d(start,start));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(0, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void turnAroundAndReturn(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = {"r", "r", "r", "r"};
        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        int start = 3;

        List<Vector2d> positions = List.of(new Vector2d(start,start));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(start, start), animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void oneAnimalRandomMovement(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] = {"f", "r", "r", "f", "r", "f", "r", "f", "f", "f", "f", "r", "f", "f", "f", "l", "f", "r", "r", "b", "r"};
        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPosition = new Vector2d(1, 2);
        Vector2d endPosition = new Vector2d(3, 4);

        List<Vector2d> positions = List.of(startPosition);
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(endPosition, animal.getPosition());
        assertEquals(animal.getOrientation(), MapDirection.WEST);
    }

    @Test
    void twoAnimalsRandomMovement(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] =
                {
                        "f", "l",
                        "f", "f",
                        "r", "f",
                        "f", "l",
                        "r", "b",
                        "b", "b",
                        "b", "l",
                        "l", "l",
                        "f", "f",
                        "r", "r",
                        "b", "r",
                        "f"
                };
        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPositionAnimalOne = new Vector2d(0, 0);
        Vector2d startPositionAnimalTwo = new Vector2d(4, 0);
        Vector2d endPositionAnimalOne = new Vector2d(2, 3);
        Vector2d endPositionAnimalTwo = new Vector2d(2, 4);

        List<Vector2d> positions = List.of(startPositionAnimalOne, startPositionAnimalTwo);
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animalTwo = simulation.getAnimals().get(0);
        Animal animalOne = simulation.getAnimals().get(1);

        assertEquals(endPositionAnimalOne, animalOne.getPosition());
        assertEquals(endPositionAnimalTwo, animalTwo.getPosition());

        assertEquals(animalOne.getOrientation(), MapDirection.SOUTH);
        assertEquals(animalTwo.getOrientation(), MapDirection.SOUTH);
    }

    @Test
    void fourAnimalsRandomMovement(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        String directionsArr[] =
                       {"f", "r", "f", "b",
                        "r", "f", "l", "f",
                        "f", "l", "f", "f",
                        "f", "f", "l", "r",
                        "r", "l", "f", "f",
                        "f", "f", "f", "r",
                        "f", "r", "l", "r"
                       };
        List<MoveDirection> directions = OptionsParser.parse(directionsArr);

        Vector2d startPositionAnimalOne = new Vector2d(0, 3);
        Vector2d startPositionAnimalTwo = new Vector2d(2, 1);
        Vector2d startPositionAnimalThree = new Vector2d(3, 3);
        Vector2d startPositionAnimalFour = new Vector2d(1, 0);
        Vector2d endPositionAnimalOne = new Vector2d(1, 2);
        Vector2d endPositionAnimalTwo = new Vector2d(3, 2);
        Vector2d endPositionAnimalThree = new Vector2d(2, 3);
        Vector2d endPositionAnimalFour = new Vector2d(2, 2);

        List<Vector2d> positions = List.of(startPositionAnimalOne, startPositionAnimalTwo, startPositionAnimalThree, startPositionAnimalFour);
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        Animal animalOne = simulation.getAnimals().get(0);
        Animal animalTwo = simulation.getAnimals().get(1);
        Animal animalThree = simulation.getAnimals().get(2);
        Animal animalFour = simulation.getAnimals().get(3);

        assertEquals(endPositionAnimalOne, animalOne.getPosition());
        assertEquals(endPositionAnimalTwo, animalTwo.getPosition());
        assertEquals(endPositionAnimalThree, animalThree.getPosition());
        assertEquals(endPositionAnimalFour, animalFour.getPosition());

        assertEquals(animalOne.getOrientation(), MapDirection.SOUTH);
        assertEquals(animalTwo.getOrientation(), MapDirection.NORTH);
        assertEquals(animalThree.getOrientation(), MapDirection.EAST);
        assertEquals(animalFour.getOrientation(), MapDirection.WEST);

    }

}
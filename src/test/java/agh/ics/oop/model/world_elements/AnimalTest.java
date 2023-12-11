package agh.ics.oop.model.world_elements;

import agh.ics.oop.World;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.maps.RectangularMap;
import agh.ics.oop.model.world_elements.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private final static int MAP_SIZE = 4;
    private final Vector2d startPosition = new Vector2d(2,2);
    private final Animal animal = new Animal(startPosition);

    @Test
    void testIsAtStart(){
        assertTrue(animal.isAt(new Vector2d(2,2)));
    }

    @Test
    void testIsAtStartFalse(){
        assertFalse(animal.isAt(new Vector2d(-2,-2)));
    }

    @Test
    void testMoveForward(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        animal.move(MoveDirection.FORWARD, map);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY() + 1);
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveRight(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        animal.move(MoveDirection.RIGHT, map);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY());
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveLeft(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        animal.move(MoveDirection.LEFT, map);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY());
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveBackward(){
        RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
        animal.move(MoveDirection.BACKWARD, map);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY() - 1);
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

}
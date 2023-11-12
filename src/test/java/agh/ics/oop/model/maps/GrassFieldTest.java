package agh.ics.oop.model.maps;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.world_elements.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrassFieldTest {
    @Test
    void placeTest(){
        Animal testAnimal1 = new Animal();
        Animal testAnimal2 = new Animal();
        GrassField map = new GrassField(10);
        assertTrue(map.place(testAnimal1));
        assertFalse(map.place(testAnimal2));
        Assertions.assertEquals(testAnimal1.getPosition(), new Vector2d(2,2));
        assertTrue(map.objectAt(new Vector2d(2,2)).equals(testAnimal1));
    }

    @Test
    void isOccupiedTest(){
        Animal testAnimal = new Animal();
        GrassField map = new GrassField(10);
        map.place(testAnimal);
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void canMoveToTest(){
        Animal testAnimal = new Animal(4, 4);
        GrassField map = new GrassField(10);
        map.place(testAnimal);
        assertTrue(map.canMoveTo(new Vector2d(511,-3245)));
        assertTrue(map.canMoveTo(new Vector2d(1,-3245)));
        assertTrue(map.canMoveTo(new Vector2d(509,4)));
        assertTrue(map.canMoveTo(new Vector2d(408,-56)));
    }

    @Test
    void moveForwardTest(){
        Animal testAnimal = new Animal();
        GrassField map = new GrassField(10);
        map.place(testAnimal);
        map.move(testAnimal, MoveDirection.FORWARD);
        assertEquals(testAnimal.getPosition(), new Vector2d(2,3));
        Assertions.assertEquals(testAnimal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void moveBackwardTest(){
        Animal testAnimal = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        map.move(testAnimal, MoveDirection.BACKWARD);
        assertEquals(testAnimal.getPosition(), new Vector2d(2,1));
        assertEquals(testAnimal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void turnRightTest(){
        Animal testAnimal = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        map.move(testAnimal, MoveDirection.RIGHT);
        assertEquals(testAnimal.getPosition(), new Vector2d(2,2));
        assertEquals(testAnimal.getOrientation(), MapDirection.EAST);
    }

    @Test
    void turnLeftTest(){
        Animal testAnimal = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        map.move(testAnimal, MoveDirection.LEFT);
        assertEquals(testAnimal.getPosition(), new Vector2d(2,2));
        assertEquals(testAnimal.getOrientation(), MapDirection.WEST);
    }


}

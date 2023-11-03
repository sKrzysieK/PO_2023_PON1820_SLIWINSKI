package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void placeTest(){
        Animal testAnimal1 = new Animal();
        Animal testAnimal2 = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        assertTrue(map.place(testAnimal1));
        assertFalse(map.place(testAnimal2));
        assertEquals(testAnimal1.getPosition(), new Vector2d(2,2));
    }

    @Test
    void isOccupiedTest(){
        Animal testAnimal = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void canMoveToTest(){
        Animal testAnimal = new Animal(4, 4);
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        assertFalse(map.canMoveTo(new Vector2d(5,5)));
        assertFalse(map.canMoveTo(new Vector2d(4,5)));
        assertFalse(map.canMoveTo(new Vector2d(5,4)));
        assertFalse(map.canMoveTo(new Vector2d(4,4)));
    }

    @Test
    void moveForwardTest(){
        Animal testAnimal = new Animal();
        RectangularMap map = new RectangularMap(4,4);
        map.place(testAnimal);
        map.move(testAnimal, MoveDirection.FORWARD);
        assertEquals(testAnimal.getPosition(), new Vector2d(2,3));
        assertEquals(testAnimal.getOrientation(), MapDirection.NORTH);
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
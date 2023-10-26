package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Vector2d startPosition = new Vector2d(2,2);
    Animal animal = new Animal(startPosition.getX(), startPosition.getY());

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
        animal.move(MoveDirection.FORWARD);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY() + 1);
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveRight(){
        animal.move(MoveDirection.RIGHT);
        Vector2d nextPosition = new Vector2d(startPosition.getX() + 1, startPosition.getY());
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveLeft(){
        animal.move(MoveDirection.LEFT);
        Vector2d nextPosition = new Vector2d(startPosition.getX() - 1, startPosition.getY());
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

    @Test
    void testMoveBackward(){
        animal.move(MoveDirection.BACKWARD);
        Vector2d nextPosition = new Vector2d(startPosition.getX(), startPosition.getY() - 1);
        assertEquals(animal.getPosition(), nextPosition);
        assertTrue(animal.isAt(nextPosition));
    }

}
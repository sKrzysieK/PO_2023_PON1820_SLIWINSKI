package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {
    @Test
    void placeTest(){
        TextMap map = new TextMap();
        assertTrue(map.place("aaaa"));
        assertTrue(map.place("bbbb"));
        assertTrue(map.place("cccc"));
        assertTrue(map.place("dddd"));
        assertEquals(map.getLength(), 4);
    }

    @Test
    void moveRightTest(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        map.move("aaaa", MoveDirection.RIGHT);
        assertEquals(map.objectAt(1), "aaaa");
        assertEquals(map.objectAt(0), "bbbb");
    }

    @Test
    void leftRightTest(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        map.move("cccc", MoveDirection.LEFT);
        assertEquals(map.objectAt(1), "cccc");
        assertEquals(map.objectAt(2), "bbbb");
    }

    @Test
    void moveOutOfBoundaryRightTest(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        for(int i = 0; i < 100; i++){
            map.move("aaaa", MoveDirection.RIGHT);
        }
        assertEquals(map.objectAt(0), "bbbb");
        assertEquals(map.objectAt(1), "cccc");
        assertEquals(map.objectAt(2), "dddd");
        assertEquals(map.objectAt(3), "aaaa");
    }

    @Test
    void moveOutOfBoundaryLeftTest(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        for(int i = 0; i < 100; i++){
            map.move("dddd", MoveDirection.LEFT);
        }
        assertEquals(map.objectAt(0), "dddd");
        assertEquals(map.objectAt(1), "aaaa");
        assertEquals(map.objectAt(2), "bbbb");
        assertEquals(map.objectAt(3), "cccc");
    }

    @Test
    void moveUnsupportedDirection1Test(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        map.move("aaaa", MoveDirection.FORWARD);
        assertEquals(map.objectAt(0), "aaaa");
        assertEquals(map.objectAt(1), "bbbb");
        assertEquals(map.objectAt(2), "cccc");
        assertEquals(map.objectAt(3), "dddd");
    }

    @Test
    void moveUnsupportedDirection2Test(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        map.move("aaaa", MoveDirection.BACKWARD);
        assertEquals(map.objectAt(0), "aaaa");
        assertEquals(map.objectAt(1), "bbbb");
        assertEquals(map.objectAt(2), "cccc");
        assertEquals(map.objectAt(3), "dddd");
    }

    @Test
    void moveNonExistentItemTest(){
        TextMap map = new TextMap();
        map.place("aaaa");
        map.place("bbbb");
        map.place("cccc");
        map.place("dddd");
        map.move("pppp", MoveDirection.LEFT);
        map.move("abcd", MoveDirection.RIGHT);
        assertEquals(map.objectAt(0), "aaaa");
        assertEquals(map.objectAt(1), "bbbb");
        assertEquals(map.objectAt(2), "cccc");
        assertEquals(map.objectAt(3), "dddd");
    }

}
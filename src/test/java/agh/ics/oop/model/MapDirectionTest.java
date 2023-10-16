package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;

        assertEquals(east, north.next());
        assertEquals(south, east.next());
        assertEquals(west, south.next());
        assertEquals(north, west.next());
    }

    @Test
    void previous() {
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;

        assertEquals(north, east.previous());
        assertEquals(east, south.previous());
        assertEquals(south, west.previous());
        assertEquals(west, north.previous());
    }
}
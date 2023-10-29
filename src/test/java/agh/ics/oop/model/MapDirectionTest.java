package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    MapDirection north = MapDirection.NORTH;
    MapDirection east = MapDirection.EAST;
    MapDirection south = MapDirection.SOUTH;
    MapDirection west = MapDirection.WEST;

    @Test
    void next() {
        assertEquals(east, north.next());
        assertEquals(south, east.next());
        assertEquals(west, south.next());
        assertEquals(north, west.next());
    }

    @Test
    void previous() {
        assertEquals(north, east.previous());
        assertEquals(east, south.previous());
        assertEquals(south, west.previous());
        assertEquals(west, north.previous());
    }
}
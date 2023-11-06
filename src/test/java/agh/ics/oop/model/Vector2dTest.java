package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    private final Vector2d v1 = new Vector2d(2, -5);
    private final Vector2d v2 = new Vector2d(-20, 13);
    private final Vector2d v3 = new Vector2d(33, -56);
    private final Vector2d v4 = new Vector2d(6, -17);
    private final Vector2d v5 = new Vector2d(33, -15);
    private final Vector2d null_vector = new Vector2d(0, 0);
    private final Vector2d v1_copy = new Vector2d(2, -5);


    @Test
    void toStringTest() {
        assertEquals("(2, -5)", v1.toString());
        assertEquals("(-20, 13)", v2.toString());
        assertEquals("(33, -56)", v3.toString());
        assertEquals("(6, -17)", v4.toString());
        assertEquals("(33, -15)", v5.toString());
    }

    @Test
    void precedesTest() {
        assertTrue(v4.precedes(v5));
        assertFalse(v5.precedes(v4));

        assertTrue(v3.precedes(v5));
        assertFalse(v5.precedes(v3));

        assertFalse(v5.precedes(v2));
        assertFalse(v4.precedes(v3));
    }

    @Test
    void vectorShouldPrecedeItself(){
        assertTrue(v1.precedes(v1_copy));
        assertTrue(v1_copy.precedes(v1_copy));
        assertTrue(v1_copy.precedes(v1));
    }

    @Test
    void followsTest() {
        assertFalse(v4.follows(v5));
        assertTrue(v5.follows(v4));

        assertFalse(v3.follows(v5));
        assertTrue(v5.follows(v3));

        assertFalse(v5.follows(v2));
        assertFalse(v4.follows(v3));

    }

    @Test
    void vectorShouldFollowItself(){
        assertTrue(v1.follows(v1_copy));
        assertTrue(v1_copy.follows(v1_copy));
        assertTrue(v1_copy.follows(v1));
    }

    @Test
    void addTest() {
        Vector2d v1_v2_sum = new Vector2d(v1.getX() + v2.getX(), v1.getY() + v2.getY());
        Vector2d v2_v5_sum = new Vector2d(v2.getX() + v5.getX(), v2.getY() + v5.getY());
        Vector2d v3_v4_sum = new Vector2d(v3.getX() + v4.getX(), v3.getY() + v4.getY());

        assertEquals(v1_v2_sum, v1.add(v2));
        assertEquals(v2_v5_sum, v2.add(v5));
        assertEquals(v3_v4_sum, v3.add(v4));
    }

    @Test
    void subtractTest() {
        Vector2d v1_v2_diff = new Vector2d(v1.getX() - v2.getX(), v1.getY() - v2.getY());
        Vector2d v2_v5_diff = new Vector2d(v2.getX() - v5.getX(), v2.getY() - v5.getY());
        Vector2d v3_v4_diff = new Vector2d(v3.getX() - v4.getX(), v3.getY() - v4.getY());

        assertEquals(v1_v2_diff, v1.subtract(v2));
        assertEquals(v2_v5_diff, v2.subtract(v5));
        assertEquals(v3_v4_diff, v3.subtract(v4));
    }

    @Test
    void upperRightTest() {
        Vector2d v1_v2_upper_right = new Vector2d(
                v1.getX() >= v2.getX() ? v1.getX() : v2.getX(),
                v1.getY() >= v2.getY() ? v1.getY() : v2.getY()
                );
        Vector2d v2_v5_upper_right = new Vector2d(
                v2.getX() >= v5.getX() ? v2.getX() : v5.getX(),
                v2.getY() >= v5.getY() ? v2.getY() : v5.getY()
        );
        Vector2d v3_v4_upper_right = new Vector2d(
                v3.getX() >= v4.getX() ? v3.getX() : v4.getX(),
                v3.getY() >= v4.getY() ? v3.getY() : v4.getY()
        );

        assertEquals(v1_v2_upper_right, v1.upperRight(v2));
        assertEquals(v2_v5_upper_right, v2.upperRight(v5));
        assertEquals(v3_v4_upper_right, v3.upperRight(v4));
    }

    @Test
    void vectorShouldBeItsUpperRight(){
        assertEquals(v1, v1.upperRight(v1_copy));
    }

    @Test
    void lowerLeft() {
        Vector2d v1_v2_lower_left = new Vector2d(
                v1.getX() <= v2.getX() ? v1.getX() : v2.getX(),
                v1.getY() <= v2.getY() ? v1.getY() : v2.getY()
        );
        Vector2d v2_v5_lower_left = new Vector2d(
                v2.getX() <= v5.getX() ? v2.getX() : v5.getX(),
                v2.getY() <= v5.getY() ? v2.getY() : v5.getY()
        );
        Vector2d v3_v4_lower_left = new Vector2d(
                v3.getX() <= v4.getX() ? v3.getX() : v4.getX(),
                v3.getY() <= v4.getY() ? v3.getY() : v4.getY()
        );

        assertEquals(v1_v2_lower_left, v1.lowerLeft(v2));
        assertEquals(v2_v5_lower_left, v2.lowerLeft(v5));
        assertEquals(v3_v4_lower_left, v3.lowerLeft(v4));
    }

    @Test
    void vectorShouldBeItsLowerLeft(){
        assertEquals(v1,v1.lowerLeft(v1_copy));
    }

    @Test
    void oppositeTest() {
        assertEquals(v1.opposite(), new Vector2d(-v1.getX(), -v1.getY()));
        assertEquals(v2.opposite(), new Vector2d(-v2.getX(), -v2.getY()));
        assertEquals(v3.opposite(), new Vector2d(-v3.getX(), -v3.getY()));
        assertEquals(v4.opposite(), new Vector2d(-v4.getX(), -v4.getY()));
        assertEquals(v5.opposite(), new Vector2d(-v5.getX(), -v5.getY()));
    }

    @Test
    void nullVectorOppositeShouldBeNullVector(){
        assertEquals(null_vector, null_vector.opposite());
    }

    @Test
    void vectorEqualsItself() {
        assertTrue(v1.equals(v1_copy));
    }

    @Test
    void vectorEqualsItsDoubleNegation(){
        Vector2d v1_opposite = v1.opposite();
        assertTrue(v1.equals(v1_opposite.opposite()));
    }

    @Test
    void vectorEqualsNewVectorWithSameCoords(){
        assertTrue(v3.equals(new Vector2d(v3.getX(), v3.getY())));
        assertTrue(v5.equals(new Vector2d(v5.getX(), v5.getY())));
    }
}
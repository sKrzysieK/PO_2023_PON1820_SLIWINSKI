package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    String mistake1[] = {"p", "b", "t", "f", "prawo", "r", "inny", "g", "l"};
    MoveDirection directions1[] = {
            MoveDirection.BACKWARD,
            MoveDirection.FORWARD,
            MoveDirection.RIGHT,
            MoveDirection.LEFT
    };

    String mistake2[] = {"abc", "b", "fff", "d", "prawo", "r", "hello", "", "l", "left", "f"};
    MoveDirection directions2[] = {
            MoveDirection.BACKWARD,
            MoveDirection.RIGHT,
            MoveDirection.LEFT,
            MoveDirection.FORWARD
    };

    @Test
    void stepTest(){
        String forward_arr[] = {"f"};
        MoveDirection forward_arr_enum[] = {MoveDirection.FORWARD};
        String right_arr[] = {"r"};
        MoveDirection right_arr_enum[] = {MoveDirection.RIGHT};
        String backward_arr[] = {"b"};
        MoveDirection backward_arr_enum[] = {MoveDirection.BACKWARD};
        String left_arr[] = {"l"};
        MoveDirection left_arr_enum[] = {MoveDirection.LEFT};

        assertArrayEquals(OptionsParser.parse(forward_arr), forward_arr_enum);
        assertArrayEquals(OptionsParser.parse(right_arr), right_arr_enum);
        assertArrayEquals(OptionsParser.parse(backward_arr), backward_arr_enum);
        assertArrayEquals(OptionsParser.parse(left_arr), left_arr_enum);
    }

    @Test
    void array1Test(){
        String arr1[] = {"f", "r", "b", "l"};
        MoveDirection arr1_enum[] = {
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        };
        assertArrayEquals(OptionsParser.parse(arr1), arr1_enum);
    }

    @Test
    void array2Test(){
        String arr2[] = {"f", "r", "r", "f"};
        MoveDirection arr2_enum[] = {
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        };

        assertArrayEquals(OptionsParser.parse(arr2), arr2_enum);
    }

    @Test
    void array3Test(){
        String arr3[] = {"r", "f", "b", "l"};
        MoveDirection arr3_enum[] = {
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        };

        assertArrayEquals(OptionsParser.parse(arr3), arr3_enum);
    }

    @Test
    void array4Test(){
        String arr4[] = {"f", "f", "b", "l"};
        MoveDirection arr4_enum[] = {
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        };

        assertArrayEquals(OptionsParser.parse(arr4), arr4_enum);
    }

    @Test
    void wrongDirectionsSholdBeIgnored(){
        assertArrayEquals(OptionsParser.parse(mistake1), directions1);
        assertArrayEquals(OptionsParser.parse(mistake2), directions2);
    }

    @Test
    void parsedArrayShouldHaveCorrectSize(){
        assertEquals(OptionsParser.parse(mistake1).length, directions1.length);
        assertEquals(OptionsParser.parse(mistake2).length, directions2.length);
    }


}
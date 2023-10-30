package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    private final String mistake1[] = {"p", "b", "t", "f", "prawo", "r", "inny", "g", "l"};
    private final LinkedList<MoveDirection> directions1 = new LinkedList<>(Arrays.asList(
            MoveDirection.BACKWARD,
            MoveDirection.FORWARD,
            MoveDirection.RIGHT,
            MoveDirection.LEFT
    ));

    private final String mistake2[] = {"abc", "b", "fff", "d", "prawo", "r", "hello", "", "l", "left", "f"};
    private final LinkedList<MoveDirection> directions2 = new LinkedList<>(Arrays.asList(
            MoveDirection.BACKWARD,
            MoveDirection.RIGHT,
            MoveDirection.LEFT,
            MoveDirection.FORWARD
    ));

    @Test
    void stepTest(){
        String forward_arr[] = {"f"};
        LinkedList<MoveDirection> forward_arr_enum = new LinkedList<>(Arrays.asList(MoveDirection.FORWARD));
        String right_arr[] = {"r"};
        LinkedList<MoveDirection> right_arr_enum = new LinkedList<>(Arrays.asList(MoveDirection.RIGHT));
        String backward_arr[] = {"b"};
        LinkedList<MoveDirection> backward_arr_enum = new LinkedList<>(Arrays.asList(MoveDirection.BACKWARD));
        String left_arr[] = {"l"};
        LinkedList<MoveDirection> left_arr_enum = new LinkedList<>(Arrays.asList(MoveDirection.LEFT));

        assertIterableEquals(OptionsParser.parse(forward_arr), forward_arr_enum);
        assertIterableEquals(OptionsParser.parse(right_arr), right_arr_enum);
        assertIterableEquals(OptionsParser.parse(backward_arr), backward_arr_enum);
        assertIterableEquals(OptionsParser.parse(left_arr), left_arr_enum);
    }

    @Test
    void array1Test(){
        String arr1[] = {"f", "r", "b", "l"};
        LinkedList<MoveDirection> arr1_enum = new LinkedList<>(Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        ));
        assertIterableEquals(OptionsParser.parse(arr1), arr1_enum);
    }

    @Test
    void array2Test(){
        String arr2[] = {"f", "r", "r", "f"};
        LinkedList<MoveDirection> arr2_enum = new LinkedList<>(Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD
        ));

        assertIterableEquals(OptionsParser.parse(arr2), arr2_enum);
    }

    @Test
    void array3Test(){
        String arr3[] = {"r", "f", "b", "l"};
        LinkedList<MoveDirection> arr3_enum = new LinkedList<>(Arrays.asList(
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        ));

        assertIterableEquals(OptionsParser.parse(arr3), arr3_enum);
    }

    @Test
    void array4Test(){
        String arr4[] = {"f", "f", "b", "l"};
        LinkedList<MoveDirection> arr4_enum = new LinkedList<>(Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        ));

        assertIterableEquals(OptionsParser.parse(arr4), arr4_enum);
    }

    @Test
    void wrongDirectionsShouldBeIgnored(){
        assertIterableEquals(OptionsParser.parse(mistake1), directions1);
        assertIterableEquals(OptionsParser.parse(mistake2), directions2);
    }

    @Test
    void parsedArrayShouldHaveCorrectSize(){
        assertEquals(OptionsParser.parse(mistake1).size(), directions1.size());
        assertEquals(OptionsParser.parse(mistake2).size(), directions2.size());
    }


}
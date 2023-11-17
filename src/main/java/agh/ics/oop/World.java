package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.GrassField;
import agh.ics.oop.model.maps.RectangularMap;
import java.util.List;

public class World {
//    private final static RectangularMap map = new RectangularMap(MAP_SIZE, MAP_SIZE);
    private final static GrassField map = new GrassField(20);

    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2));
        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();
    }
}

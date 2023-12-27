package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.GrassField;
import agh.ics.oop.model.maps.RectangularMap;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2));


            List<Simulation> simulations = new ArrayList<>();
            for(int i = 0; i< 1000;i++){
                RectangularMap rectMap = new RectangularMap(4, 4);
                rectMap.addListener(new ConsoleMapDisplay());
                simulations.add(new Simulation(directions, positions, rectMap));
            }

            SimulationEngine simulationEngine = new SimulationEngine(simulations);

            simulationEngine.runAsync();

            simulationEngine.awaitSimulationsEnd();

            System.out.println("System zakończył działanie.");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

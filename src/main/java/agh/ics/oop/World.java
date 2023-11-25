package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.maps.GrassField;
import agh.ics.oop.model.maps.RectangularMap;

import java.util.ArrayList;
import java.util.List;

public class World {
//    private final static RectangularMap rectMap = new RectangularMap(4, 4);
//    private final static GrassField grassMap = new GrassField(20);

    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2));

//            grassMap.addListener(new ConsoleMapDisplay());
//            rectMap.addListener(new ConsoleMapDisplay());

//            Simulation simulation1 = new Simulation(directions, positions, rectMap);
//            Simulation simulation2 = new Simulation(directions, positions, grassMap);
//            List<Simulation> simulations = List.of(simulation1, simulation2);

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

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}

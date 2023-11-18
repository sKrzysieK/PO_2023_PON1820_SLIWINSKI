package agh.ics.oop;

import agh.ics.oop.model.maps.MapChangeListener;
import agh.ics.oop.model.maps.WorldMap;

public class ConsoleMapDisplay implements MapChangeListener {
    private int logID = 0;
    public void mapChanged(WorldMap worldMap, String message){
        System.out.printf(message);
        System.out.println(worldMap);
        logID++;
        System.out.println(logID);
    }
}

package agh.ics.oop.presenter;

import agh.ics.oop.model.maps.MapChangeListener;
import agh.ics.oop.model.maps.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter implements MapChangeListener {
    WorldMap worldMap;
    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap map){
        this.worldMap = map;
    }

    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    public void drawMap(){
        synchronized (System.out){
            infoLabel.setText(worldMap.toString());
        }
    }
}

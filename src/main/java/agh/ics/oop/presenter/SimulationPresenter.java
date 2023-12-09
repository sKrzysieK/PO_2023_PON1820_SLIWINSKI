package agh.ics.oop.presenter;

import agh.ics.oop.ConsoleMapDisplay;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.maps.GrassField;
import agh.ics.oop.model.maps.MapChangeListener;
import agh.ics.oop.model.maps.RectangularMap;
import agh.ics.oop.model.maps.WorldMap;
import agh.ics.oop.model.world_elements.WorldElement;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    WorldMap worldMap;

    @FXML
    private TextField moveListTextField;
    @FXML
    private Label moveDescriptionLabel;
    @FXML
    private GridPane mapGrid;

    private static final int CELL_WIDTH = 30;
    private static final int CELL_HEIGHT = 30;

    public void setWorldMap(WorldMap map){
        this.worldMap = map;
    }

    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescriptionLabel.setText(message);
        });
    }

    public void drawMap(){
        clearGrid();
        drawGrid();
    }

    public void onSimulationStartClicked() throws Exception{
        System.out.println("click");
        List<MoveDirection> directions = OptionsParser.parse(moveListTextField.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,1));
        List<Simulation> simulations = new ArrayList<>();
        for(int i = 0; i< 1;i++){
            GrassField grassMap = new GrassField(10);
            grassMap.addListener(new ConsoleMapDisplay());
            grassMap.addListener(this);
            setWorldMap(grassMap);
            simulations.add(new Simulation(directions, positions, grassMap));
        }
        SimulationEngine simulationEngine = new SimulationEngine(simulations);
        simulationEngine.runAsync();
    }
    private void drawGrid(){
        int width = worldMap.getCurrentBounds().upperRight().getX() - worldMap.getCurrentBounds().lowerLeft().getX() + 1;
        int height = worldMap.getCurrentBounds().upperRight().getY() - worldMap.getCurrentBounds().lowerLeft().getY() + 1;

        Vector2d upperRight = worldMap.getCurrentBounds().upperRight();
        Vector2d lowerLeft = worldMap.getCurrentBounds().lowerLeft();

        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int i = lowerLeft.getY(); i <= upperRight.getY(); i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }



        for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++) {
            for (int j = upperRight.getY(); j >= lowerLeft.getY(); j--) {
                WorldElement objAtPos = worldMap.objectAt(new Vector2d(i, j));
                if(objAtPos!=null) {
                    Label label = new Label(objAtPos.toString());
                    GridPane.setHalignment(label, HPos.CENTER);
                    mapGrid.add(label, i - lowerLeft.getX(), upperRight.getY() - j);
                }
            }
        }
    }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}

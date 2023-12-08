package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.maps.RectangularMap;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();

            List<MoveDirection> directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            List<Vector2d> positions = List.of(new Vector2d(2,2));
            List<Simulation> simulations = new ArrayList<>();
            for(int i = 0; i< 1;i++){
                RectangularMap rectMap = new RectangularMap(4, 4);
                rectMap.addListener(new ConsoleMapDisplay());
                rectMap.addListener(presenter);
                presenter.setWorldMap(rectMap);
                simulations.add(new Simulation(directions, positions, rectMap));
            }
            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsync();
            simulationEngine.awaitSimulationsEnd();
            System.out.println("System zakończył działanie.");



            configureStage(primaryStage, viewRoot);
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}

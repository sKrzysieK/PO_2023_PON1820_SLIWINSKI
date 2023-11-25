package agh.ics.oop;

import java.util.List;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threads;

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
        this.threads = createThreads();
    }

    private List<Thread> createThreads() {
        return simulations.stream()
                .map(simulation -> new Thread(simulation::run))
                .toList();
    }

    public void runSync(){
        for(Simulation simulation : simulations){
            simulation.run();
        }
    }

    public void runAsync() {
        for(Thread thread : threads){
            thread.start();
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}

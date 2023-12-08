package agh.ics.oop;

import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxRange;
    private final int grassCount;
    private final List<Vector2d> positions;

    public RandomPositionGenerator(int maxRange, int grassCount) {
        this.maxRange = maxRange;
        this.grassCount = grassCount;
        this.positions = generateRandomPositions();
    }

    private List<Vector2d> generateRandomPositions() {
        List<Vector2d> allPossiblePositions = new ArrayList<>();
        for (int x = 0; x < maxRange; x++) {
            for (int y = 0; y < maxRange; y++) {
                allPossiblePositions.add(new Vector2d(x, y));
            }
        }
        Collections.shuffle(allPossiblePositions);
        return allPossiblePositions.subList(0, grassCount);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return positions.iterator();
    }
}
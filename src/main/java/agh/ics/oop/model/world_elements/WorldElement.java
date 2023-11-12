package agh.ics.oop.model.world_elements;

import agh.ics.oop.model.Vector2d;

public interface WorldElement {
    Vector2d getPosition();

    boolean isAt(Vector2d position);

    String toString();
}

package agh.ics.oop.model.world_elements;

public interface WorldElement<P> {
    P getPosition();

    boolean isAt(P position);

    String toString();
}

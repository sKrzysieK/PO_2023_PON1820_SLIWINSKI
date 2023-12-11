package agh.ics.oop.model.exceptions;

import agh.ics.oop.model.Vector2d;

public class PositionOutOfBoundariesException extends Exception{
    public PositionOutOfBoundariesException(Vector2d position){
        super("Position " + position + " is out of boundaries!");
    }
}

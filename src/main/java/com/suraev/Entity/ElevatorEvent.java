package com.suraev.Entity;

public class ElevatorEvent {
    private int destination;

    public ElevatorEvent(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }
}

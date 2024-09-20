package com.suraev.Entity;

public class Passenger implements FloorFunction, ElevatorFunction {
    private final String name;
    private final Floor floor;
    private Elevator elevator;

    public Passenger(String name,Floor floor) {
        this.name = name;
        this.floor = floor;

    }

    public String getName() {
        return name;
    }

    @Override
    public void pressButtonToCallElevator() throws InterruptedException {
        floor.pressButtonToCalElevator(this);
    }

    @Override
    public void showInfoAboutElevatorsOnDisplayFloor() {
        floor.showInfoAboutElevatorsOnDisplayFloor();
    }

    @Override
    public void pressButtonToFloor(int numberFloor) throws InterruptedException {
        elevator= floor.getClosestElevatorToFlor();
        elevator.pressButtonToFloor(numberFloor);

    }

    @Override
    public void pressButtonToCloseDoor() {
        elevator.pressButtonToCloseDoor();
    }

    @Override
    public void pressButtonToOpenDoor() {
        elevator.pressButtonToOpenDoor();
    }

    @Override
    public void pressButtonToCallDispatcher() {
        elevator.pressButtonToCallDispatcher();
    }

    @Override
    public void showInfoOnDisplayOfElevator() {
        elevator = floor.getClosestElevatorToFlor();
        elevator.showInfoOnDisplayOfElevator();
    }
}

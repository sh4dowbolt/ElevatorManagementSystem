package com.suraev.Entity;

public interface ElevatorFunction {
    void pressButtonToFloor(int floor) throws InterruptedException;
    void pressButtonToCloseDoor();
    void pressButtonToOpenDoor();
    void pressButtonToCallDispatcher();
    void showInfoOnDisplayOfElevator();
}

package com.suraev.Entity;

public class Floor {

    private final int floorNumber;
    private final Elevator elevator1;
    private final Elevator elevator2;
    private boolean isElevatorCalled;

    public Floor(Elevator elevatorFirst, Elevator elevatorSecond, int floorNumber) {

        this.elevator1 = elevatorFirst;
        this.elevator2 = elevatorSecond;
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void showInfoAboutElevatorsOnDisplayFloor() {
        elevator1.showInfoOnDisplayOfElevator();
        elevator2.showInfoOnDisplayOfElevator();
        if(isElevatorCalled) {
            System.out.println(String.format("Лифт №%s вызван", getClosestElevatorToFlor().getNumber()));
        }
    }

    public void pressButtonToCalElevator(Passenger passenger) throws InterruptedException {
        Elevator elevator = getClosestElevatorToFlor();
        System.out.println(String.format("Пассажир %s нажал кнопку вызова на %s этаже",passenger.getName(),this.floorNumber));
        elevator.callFromFloor(this);
        isElevatorCalled();
        System.out.println(String.format("Лифт на этаже %s",floorNumber));
        System.out.println(String.format("Пассажир %s заходит в кабину лифта %s",passenger.getName(),elevator.getNumber()));
        Thread.sleep(1000);
    }

    public Elevator getClosestElevatorToFlor() {
        return closestElevatorToFloor();
    }

    private boolean isElevatorCalled() {
        return isElevatorCalled=true;
    }

    private Elevator closestElevatorToFloor() {
        int elevatorFirstAndFloorDiff = Math.abs(floorNumber - elevator1.getCurrentFloor());
        int elevatorSecondAndFloorDiff = Math.abs(floorNumber - elevator2.getCurrentFloor());

        return elevatorSecondAndFloorDiff < elevatorFirstAndFloorDiff  ? elevator2 : elevator1;
    }






}

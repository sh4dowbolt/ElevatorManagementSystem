package com.suraev.Entity;

public class ElevatorSimulation {

    private Elevator[] elevators;

    Elevator elevator1 = new Elevator(1,1);
    Elevator elevator2 = new Elevator(2,1);

    Floor floor = new Floor(elevator1,elevator2,1);
    Floor floor2 = new Floor(elevator1,elevator2,1);

    Passenger passenger1= new Passenger("Ryan Gosling",floor);
    Passenger passenger2= new Passenger("Mikhail Zubenko",floor2);

    public void start() {
        Elevator e1 = new Elevator();
        Elevator e2 = new Elevator();
        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(e2);

        Elevator [] elevators1 ={e1, e2};
        elevators=elevators1;
        // стартуем треды

        t1.start();
        t2.start();

        while(!Thread.interrupted()) {
            passenger1.pressButtonToCallElevator();
            passenger1.pressButtonToFloor(15);
            passenger2.pressButtonToCallElevator();
            passenger2.pressButtonToFloor(5);
        }
        t1.interrupt();
        t2.interrupt();

    }

}

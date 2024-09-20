package com.suraev;

import com.suraev.Entity.Elevator;
import com.suraev.Entity.Floor;
import com.suraev.Entity.Passenger;


public class Runner {
    public static void main(String[] args) throws InterruptedException {

        Elevator elevator1 = new Elevator(1,1);
        Elevator elevator2 = new Elevator(2,1);

        Floor floor = new Floor(elevator1,elevator2,1);
        Floor floor2 = new Floor(elevator1,elevator2,1);

        Passenger passenger1= new Passenger("Ryan Gosling",floor);
        Passenger passenger2= new Passenger("Mikhail Zubenko",floor2);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    passenger1.pressButtonToCallElevator();
                   // passenger1.pressButtonToFloor(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        thread.start();


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    passenger2.pressButtonToCallElevator();
                   // passenger2.pressButtonToFloor(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();





    }

}
package com.suraev.Entity;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public class Elevator implements ElevatorFunction, ElevatorMovable, Runnable{
    public static final int MIN_FLOOR = 1;
    public static final int MAX_FLOOR = 20;
    private final int elevatorId;
    private int currentFloor;
    private boolean isCalled;
    // запросы пассажиров по кнопке в лифте
    private ArrayList<ElevatorEvent> callsFromFloor;
    // запросы пассажиров с этажа
    private ArrayList<ElevatorEvent> callsFromButton;

    private boolean down;

    Condition condition = Condition.withOpenDoor;



    // создаем ивент когда пользователь нажимает на кнопку в лифте
    public void createElevatorEventForButton(int d){
        ElevatorEvent EE = new ElevatorEvent(d);
        addCallsFromButton(EE);
    }
    //добавляем запрос по кнопке в лист
    public void addCallsFromButton(ElevatorEvent ee){
        callsFromButton.add(ee);
    }
    // создаем ивент когда пользователь нажимает кнопку с этажа
    public void createElevatorEventForCall(int d){
        ElevatorEvent EE = new ElevatorEvent(d);
        addCallsFromFloor(EE);
    }
    //добавляем запрос с кнопки с этажа в лист
    public void addCallsFromFloor(ElevatorEvent ee){
        callsFromFloor.add(ee);
    }

    public void approachingManagementElevator() {

    }



    @Override
    public void run() {
        // запускаем поток, пока он не прерван
        while (!Thread.interrupted()) {
            down = false;
            // проверяем на запросы, когда лифт свободен, ждем нажатия кнопок
            if (callsFromButton.isEmpty() && callsFromFloor.isEmpty()) {
                wait();
            }
            // проверяем нажимал ли кто то на кнопку
            if (!callsFromFloor.isEmpty()) {
                int currentDestinationOfElevator;
                // если есть вызов с этажа, получаем его
                ElevatorEvent elevatorEvent = callsFromFloor.get(0);
                if(down) {
                    if(elevatorEvent.getDestination()>currentFloor) {

                    }


                } else {
                    // двигаемся до вызывающего
                    move(elevatorEvent);
                    // удаляем информацию до какого этажа нужно было добраться
                    callsFromFloor.remove(0);
                }

                // затем проверяем какую кнопку движения он нажмет
                if(!callsFromButton.isEmpty()) {
                    //если нажал кнопку, двигаемся до заданного этажа
                    ElevatorEvent elevatorEvent1 = callsFromButton.get(0);
                    if(elevatorEvent1.getDestination()>currentFloor) {
                        down = false;
                    } else {
                        down =true;
                    }
                    currentDestinationOfElevator = elevatorEvent1.getDestination();
                    move(elevatorEvent);
                    callsFromButton.remove(0);
                }


                }

            }

        }

    public Elevator(int elevatorId, int currentFloor) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getCondition() {

        return condition.value;
    }

    @Override
    public void showInfoOnDisplayOfElevator() {
        String.format("""
                Текущий этаж: %s 
                Статус лифта: %s
                """, getCurrentFloor(), getCondition());
    }

    // вызываем с этажа
    public void callFromFloor(Floor floor) throws InterruptedException {
        // проверка: если лифт сразу недоступен, должен закрывать двери, а потом спускаться
        if (this.currentFloor != floor.getFloorNumber()) {
            condition = Condition.isClosingDoor;
            System.out.println(String.format("Лифт №%s на этаже %s: %s", this.number, this.getCurrentFloor(), condition.value));
            Thread.sleep(500);
        }
        // создаем ивент вызова, добавляю в лист вызовов с этажей
        createElevatorEventForCall(floor.getFloorNumber());
        isCalled=true;
        Thread.sleep(100);
        //move(floor.getFloorNumber());
        condition = Condition.isOpeningDoor;
        System.out.println(String.format("Лифт №%s на этаже %s: %s", this.number, this.getCurrentFloor(), condition.value));
    }

    // нажимаем кнопку в лифте
    public void pressButtonToFloor(int floor) throws InterruptedException {
        System.out.println("Пассажир нажимает на кнопку этажа " + floor);
        Thread.sleep(500);
        if (isValidFloor(floor)) {

            // создаем ивент вызова, добавляю в лист вызовов с кнопки лифта
            // добавляем в случае, когда лифт движется по пути. Когда
            createElevatorEventForButton(floor);
                    condition = Condition.isClosingDoor;
                    System.out.println(String.format("Лифт №%s на этаже %s: %s", this.number, this.getCurrentFloor(), condition.value));
                    //move(floor);
                    condition = Condition.isOpeningDoor;
                    System.out.println(String.format("Лифт №%s на этаже %s: %s", this.number, this.getCurrentFloor(), condition.value));
                    Thread.sleep(500);
                    System.out.println("Пассажир вышел");

            }


    }

    private boolean isValidFloor(int floor) {
        return floor >= MIN_FLOOR & floor <= MAX_FLOOR;
    }

    private boolean isAnyMove() {
        // логика метода на проверку движения
        System.out.println("Датчик движения не обнаружил движения");
        return false;
    }

    @Override
    public void pressButtonToCloseDoor() {
        System.out.println("Дверь лифта закрывается");
        condition = Condition.isClosingDoor;
    }

    @Override
    public void pressButtonToOpenDoor() {
        System.out.println("Дверь лифта открывается");
        condition = Condition.isOpeningDoor;
    }

    @Override
    public void pressButtonToCallDispatcher() {
        System.out.println("Происходит вызов диспетчера");
    }


    public  void advancedMove(ArrayList<ElevatorEvent> callsFromFloor,ArrayList<ElevatorEvent> callsFromButton) {


        for (ElevatorEvent e1 : callsFromFloor) {
            int destination = e1.getDestination();
            move(destination);
            for (ElevatorEvent e2 : callsFromButton) {
                int destination1 = e2.getDestination();
                move(destination1);
            }
        }
    }
    public void move(int destination){
        while (currentFloor != floor) {

            if (floor > currentFloor) {
                currentFloor++;
                condition = Condition.isMovingUp;
                System.out.println(String.format("Лифт №%s %s на: %s этаж", number, condition.value, currentFloor));
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (floor < currentFloor) {
                currentFloor--;
                condition = Condition.isMovingDown;
                System.out.println(String.format("Лифт №%s %s на %s этаж", number, condition.value, currentFloor));
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

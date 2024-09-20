package com.suraev.Entity;

public enum Condition {

    isMovingUp("движется вверх"),
    isMovingDown("движется вниз"),
    isOpeningDoor("двери открываются"),
    isClosingDoor("двери закрываются"),
    withOpenDoor("двери открыты");

    public final String value;

    Condition(String value1) {
        this.value = value1;
    }
}

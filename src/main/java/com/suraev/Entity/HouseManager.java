package com.suraev.Entity;

public class HouseManager {
    private static Floor[] floors;

    public HouseManager() {
        floors = new Floor[20];
        for(int i=0; i<5;i++) {
            floors[i] = new Floor();
        }
    }
}

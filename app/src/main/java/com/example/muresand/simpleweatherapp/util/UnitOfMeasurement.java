package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/5/2017.
 */

public enum UnitOfMeasurement {

    METRIC ("metric"),
    IMPERIAL ("imperial"),
    DEFAULT ("standard");

    private String name;
    UnitOfMeasurement(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/5/2017.
 */

public enum UnitOfMeasurement {

    METRIC ("metric", "Celsius"),
    IMPERIAL ("imperial", "Fahrenheit"),
    DEFAULT ("standard", "Celsius");

    private String name;
    private String degreeUnit;

    UnitOfMeasurement(String name, String degreeUnit) {
        this.name = name;
        this.degreeUnit = degreeUnit;
    }

    public String getName() {
        return this.name;
    }

    public String getAppropriateDegreeUnit() {
        return this.degreeUnit;
    }

    public static UnitOfMeasurement getEnumValueFromName(String name) {
        if (name.equals("metric")) {
            return METRIC;
        }
        else if (name.equals("imperial")) {
            return IMPERIAL;
        }
        else if (name.equals("standard")) {
            return DEFAULT;
        }

        return METRIC;
    }
}

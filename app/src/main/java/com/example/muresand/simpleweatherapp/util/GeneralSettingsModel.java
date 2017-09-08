package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/7/2017.
 */

public class GeneralSettingsModel {

    private boolean mAutoDetectLocation;
    private boolean mAnimationsEnabled;
    private UnitOfMeasurement mUnitOfMeasurement;
    private int mNumberOfDaysInForecast;
    private LocationModel mLocationModel;

    public GeneralSettingsModel() {
    }

    public GeneralSettingsModel(boolean autoDetectLocation, boolean animationsEnabled,
                                UnitOfMeasurement unitOfMeasurement, int numberOfDaysInForecast, LocationModel locationModel) {
        this.mAnimationsEnabled = animationsEnabled;
        this.mAutoDetectLocation = autoDetectLocation;
        this.mUnitOfMeasurement = unitOfMeasurement;
        this.mNumberOfDaysInForecast = numberOfDaysInForecast;
        mLocationModel = locationModel;
    }

    public boolean isAutoDetectLocation() {
        return mAutoDetectLocation;
    }

    public void setAutoDetectLocation(boolean mAutoDetectLocation) {
        this.mAutoDetectLocation = mAutoDetectLocation;
    }

    public boolean isAnimationsEnabled() {
        return mAnimationsEnabled;
    }

    public void setAnimationsEnabled(boolean mAnimationsEnabled) {
        this.mAnimationsEnabled = mAnimationsEnabled;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return mUnitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement mUnitOfMeasurement) {
        this.mUnitOfMeasurement = mUnitOfMeasurement;
    }

    public int getNumberOfDaysInForecast() {
        return mNumberOfDaysInForecast;
    }

    public void setNumberOfDaysInForecast(int numberOfDaysInForecast) {
        this.mNumberOfDaysInForecast = numberOfDaysInForecast;
    }

    public LocationModel getLocationModel() {
        return mLocationModel;
    }

    public void setLocationModel(LocationModel mLocationModel) {
        this.mLocationModel = mLocationModel;
    }
}

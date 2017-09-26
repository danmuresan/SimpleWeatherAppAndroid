package com.example.muresand.simpleweatherapp.util;

import java.util.Date;

/**
 * Created by muresand on 9/26/2017.
 */

public class WeatherItemModel {

    private String mCityName;
    private double mDegrees;
    private UnitOfMeasurement mUnitOfMeasurement;
    private String mDescription;
    private String mIconSource;
    private String mDate;

    public WeatherItemModel() {
    }

    public WeatherItemModel(String mCityName, double mDegrees, UnitOfMeasurement mUnitOfMeasurement, String mDescription, String mIconSource, String mDate) {
        this.mCityName = mCityName;
        this.mDegrees = mDegrees;
        this.mUnitOfMeasurement = mUnitOfMeasurement;
        this.mDescription = mDescription;
        this.mIconSource = mIconSource;
        this.mDate = mDate;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public double getDegrees() {
        return mDegrees;
    }

    public void setDegrees(double mDegrees) {
        this.mDegrees = mDegrees;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return mUnitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement mUnitOfMeasurement) {
        this.mUnitOfMeasurement = mUnitOfMeasurement;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getIconSource() {
        return mIconSource;
    }

    public void setIconSource(String mIconSource) {
        this.mIconSource = mIconSource;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}

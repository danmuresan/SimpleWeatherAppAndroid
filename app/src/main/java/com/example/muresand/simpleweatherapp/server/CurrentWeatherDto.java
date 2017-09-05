package com.example.muresand.simpleweatherapp.server;

import java.util.Date;

/**
 * Created by muresand on 9/5/2017.
 */

public class CurrentWeatherDto {

    private LocationDto mLocation;
    private String mWeatherMain;
    private String mWeatherDescription;
    private String mWeatherIcon;
    private double mTemperature;
    private int mHumidity;
    private double mMinTemperature;
    private double mMaxTemperature;
    private double mPressure;
    private int mCloudiness;
    private Date mDate;

    public CurrentWeatherDto() {
    }

    public CurrentWeatherDto(LocationDto mLocation, String mWeatherMain, String mWeatherDescription,
                             String mWeatherIcon, double mTemperature, int mHumidity, double mMinTemperature,
                             double mMaxTemperature, double mPressure, int mCloudiness, Date mDate) {
        this.mLocation = mLocation;
        this.mWeatherMain = mWeatherMain;
        this.mWeatherDescription = mWeatherDescription;
        this.mWeatherIcon = mWeatherIcon;
        this.mTemperature = mTemperature;
        this.mHumidity = mHumidity;
        this.mMinTemperature = mMinTemperature;
        this.mMaxTemperature = mMaxTemperature;
        this.mPressure = mPressure;
        this.mCloudiness = mCloudiness;
        this.mDate = mDate;
    }

    public LocationDto getmLocation() {
        return mLocation;
    }

    public void setmLocation(LocationDto mLocation) {
        this.mLocation = mLocation;
    }

    public String getmWeatherMain() {
        return mWeatherMain;
    }

    public void setmWeatherMain(String mWeatherMain) {
        this.mWeatherMain = mWeatherMain;
    }

    public String getmWeatherDescription() {
        return mWeatherDescription;
    }

    public void setmWeatherDescription(String mWeatherDescription) {
        this.mWeatherDescription = mWeatherDescription;
    }

    public String getmWeatherIcon() {
        return mWeatherIcon;
    }

    public void setmWeatherIcon(String mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getmMinTemperature() {
        return mMinTemperature;
    }

    public void setmMinTemperature(double mMinTemperature) {
        this.mMinTemperature = mMinTemperature;
    }

    public double getmMaxTemperature() {
        return mMaxTemperature;
    }

    public void setmMaxTemperature(double mMaxTemperature) {
        this.mMaxTemperature = mMaxTemperature;
    }

    public double getmPressure() {
        return mPressure;
    }

    public void setmPressure(double mPressure) {
        this.mPressure = mPressure;
    }

    public int getmCloudiness() {
        return mCloudiness;
    }

    public void setmCloudiness(int mCloudiness) {
        this.mCloudiness = mCloudiness;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}

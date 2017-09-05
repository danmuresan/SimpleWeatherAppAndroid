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

    public LocationDto getLocation() {
        return mLocation;
    }

    public void setLocation(LocationDto mLocation) {
        this.mLocation = mLocation;
    }

    public String getWeatherMain() {
        return mWeatherMain;
    }

    public void setWeatherMain(String mWeatherMain) {
        this.mWeatherMain = mWeatherMain;
    }

    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    public void setWeatherDescription(String mWeatherDescription) {
        this.mWeatherDescription = mWeatherDescription;
    }

    public String getWeatherIcon() {
        return mWeatherIcon;
    }

    public void setWeatherIcon(String mWeatherIcon) {
        this.mWeatherIcon = mWeatherIcon;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getMinTemperature() {
        return mMinTemperature;
    }

    public void setMinTemperature(double mMinTemperature) {
        this.mMinTemperature = mMinTemperature;
    }

    public double getMaxTemperature() {
        return mMaxTemperature;
    }

    public void setMaxTemperature(double mMaxTemperature) {
        this.mMaxTemperature = mMaxTemperature;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double mPressure) {
        this.mPressure = mPressure;
    }

    public int getCloudiness() {
        return mCloudiness;
    }

    public void setCloudiness(int mCloudiness) {
        this.mCloudiness = mCloudiness;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }
}

package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/6/2017.
 */

public class WeatherDetailedMetricsDto {

    @SerializedName("temp")
    private double mTempMain;

    @SerializedName("pressure")
    private double mPressure;

    @SerializedName("humidity")
    private int mHumidityPercentage;

    @SerializedName("temp_min")
    private double mMinTemperature;

    @SerializedName("temp_max")
    private double mMaxTemperature;

    public WeatherDetailedMetricsDto(double mTempMain, double mPressure, int mHumidityPercentage, double mMinTemperature, double mMaxTemperature) {
        this.mTempMain = mTempMain;
        this.mPressure = mPressure;
        this.mHumidityPercentage = mHumidityPercentage;
        this.mMinTemperature = mMinTemperature;
        this.mMaxTemperature = mMaxTemperature;
    }


    public double getTempMain() {
        return mTempMain;
    }

    public void setTempMain(double mTempMain) {
        this.mTempMain = mTempMain;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double mPressure) {
        this.mPressure = mPressure;
    }

    public int getHumidityPercentage() {
        return mHumidityPercentage;
    }

    public void setHumidityPercentage(int mHumidityPercentage) {
        this.mHumidityPercentage = mHumidityPercentage;
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
}

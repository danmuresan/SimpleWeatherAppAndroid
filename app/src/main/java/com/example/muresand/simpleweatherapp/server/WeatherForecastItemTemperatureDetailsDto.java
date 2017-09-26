package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/26/2017.
 */

public class WeatherForecastItemTemperatureDetailsDto {

    @SerializedName("day")
    private double mDayTemp;

    @SerializedName("min")
    private double mMinTemp;

    @SerializedName("max")
    private double mMaxTemp;

    @SerializedName("night")
    private double mNightTemp;

    @SerializedName("eve")
    private double mEveTemp;

    @SerializedName("morn")
    private double mMorningTemp;

    public WeatherForecastItemTemperatureDetailsDto() {
    }

    public WeatherForecastItemTemperatureDetailsDto(double mDayTemp, double mMinTemp, double mMaxTemp, double mNightTemp, double mEveTemp, double mMorningTemp) {
        this.mDayTemp = mDayTemp;
        this.mMinTemp = mMinTemp;
        this.mMaxTemp = mMaxTemp;
        this.mNightTemp = mNightTemp;
        this.mEveTemp = mEveTemp;
        this.mMorningTemp = mMorningTemp;
    }

    public double getDayTemp() {
        return mDayTemp;
    }

    public void setDayTemp(double mDayTemp) {
        this.mDayTemp = mDayTemp;
    }

    public double getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(double mMinTemp) {
        this.mMinTemp = mMinTemp;
    }

    public double getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(double mMaxTemp) {
        this.mMaxTemp = mMaxTemp;
    }

    public double getNightTemp() {
        return mNightTemp;
    }

    public void setNightTemp(double mNightTemp) {
        this.mNightTemp = mNightTemp;
    }

    public double getEveTemp() {
        return mEveTemp;
    }

    public void setEveTemp(double mEveTemp) {
        this.mEveTemp = mEveTemp;
    }

    public double getMorningTemp() {
        return mMorningTemp;
    }

    public void setMorningTemp(double mMorningTemp) {
        this.mMorningTemp = mMorningTemp;
    }
}

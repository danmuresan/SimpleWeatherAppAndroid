package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/6/2017.
 */

public class CoordinatesDto {

    @SerializedName("lat")
    private double mLatitude;

    @SerializedName("lon")
    private double mLongitude;

    private String mCityName;

    public CoordinatesDto() {
    }

    public CoordinatesDto(double latitude, double longitude) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public CoordinatesDto(double latitude, double longitude, String cityName) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mCityName = cityName;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }
}

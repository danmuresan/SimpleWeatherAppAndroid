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

    public CoordinatesDto() {
    }

    public CoordinatesDto(double latitude, double longitude) {
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (otherObj instanceof CoordinatesDto) {
            CoordinatesDto other = (CoordinatesDto) otherObj;
            return other.getLatitude() == mLatitude && other.getLongitude() == mLongitude;
        }

        return false;
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
}

package com.example.muresand.simpleweatherapp.server;

import android.location.Location;

/**
 * Created by muresand on 9/5/2017.
 */

public class LocationDto {

    private String mCityName;
    private String mCountry;
    private long mCityId;
    private double mLatitude;
    private double mLongitude;

    public LocationDto() {
    }

    public LocationDto(String cityName, String country, long cityId, double latitude, double longitude) {
        this.mCityName = cityName;
        this.mCountry = country;
        this.mCityId = cityId;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public long getCityId() {
        return mCityId;
    }

    public void setCityId(long mCityId) {
        this.mCityId = mCityId;
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

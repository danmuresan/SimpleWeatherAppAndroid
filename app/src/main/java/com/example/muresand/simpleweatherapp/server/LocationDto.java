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

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public long getmCityId() {
        return mCityId;
    }

    public void setmCityId(long mCityId) {
        this.mCityId = mCityId;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }
}

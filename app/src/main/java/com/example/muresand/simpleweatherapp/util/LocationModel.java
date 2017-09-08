package com.example.muresand.simpleweatherapp.util;

import android.location.Location;

import com.example.muresand.simpleweatherapp.server.CoordinatesDto;

/**
 * Created by muresand on 9/7/2017.
 */

public class LocationModel {

    private CoordinatesDto mCoordinates;
    private long mCityId;
    private String mCity;
    private String mCountry;

    public LocationModel() {
    }

    public LocationModel(CoordinatesDto mCoordinates, long mCityId, String mCity, String mCountry) {
        this.mCoordinates = mCoordinates;
        this.mCityId = mCityId;
        this.mCity = mCity;
        this.mCountry = mCountry;
    }

    public CoordinatesDto getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(CoordinatesDto mCoordinates) {
        this.mCoordinates = mCoordinates;
    }

    public long getCityId() {
        return mCityId;
    }

    public void setCityId(long mCityId) {
        this.mCityId = mCityId;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }
}

package com.example.muresand.simpleweatherapp.server;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/5/2017.
 */

public class LocationDto {

    @SerializedName("type")
    private int mType;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("id")
    private long mId;

    @SerializedName("sunrise")
    private long mSunrise;

    @SerializedName("sunset")
    private long mSunset;

    public LocationDto(int mType, String mCountry, long mId, long mSunrise, long mSunset) {
        this.mType = mType;
        this.mCountry = mCountry;
        this.mId = mId;
        this.mSunrise = mSunrise;
        this.mSunset = mSunset;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public long getSunrise() {
        return mSunrise;
    }

    public void setSunrise(long mSunrise) {
        this.mSunrise = mSunrise;
    }

    public long getSunset() {
        return mSunset;
    }

    public void setSunset(long mSunset) {
        this.mSunset = mSunset;
    }
}

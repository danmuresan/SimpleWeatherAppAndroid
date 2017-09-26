package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/26/2017.
 */

public class CityInfoDto {

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("coord")
    private CoordinatesDto mCoords;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("population")
    private long mPopulation;

    public CityInfoDto() {
    }

    public CityInfoDto(long mId, String mName, CoordinatesDto mCoords, String mCountry, long mPopulation) {
        this.mId = mId;
        this.mName = mName;
        this.mCoords = mCoords;
        this.mCountry = mCountry;
        this.mPopulation = mPopulation;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public CoordinatesDto getCoords() {
        return mCoords;
    }

    public void setCoords(CoordinatesDto mCoords) {
        this.mCoords = mCoords;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public long getPopulation() {
        return mPopulation;
    }

    public void setPopulation(long mPopulation) {
        this.mPopulation = mPopulation;
    }
}

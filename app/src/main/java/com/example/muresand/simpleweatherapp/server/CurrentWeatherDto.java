package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muresand on 9/5/2017.
 */

public class CurrentWeatherDto implements WeatherResponseMarker {

    @SerializedName("coord")
    private CoordinatesDto mCoordinates;

    @SerializedName("weather")
    private List<WeatherDto> mWeather;

    @SerializedName("base")
    private String mBase;

    @SerializedName("main")
    private WeatherDetailedMetricsDto mMainWeatherMetrics;

    @SerializedName("id")
    private long mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("cod")
    private int mCode;

    @SerializedName("dt")
    private long mDate;

    @SerializedName("sys")
    private LocationDto mLocationInfo;

    public CurrentWeatherDto(CoordinatesDto mCoordinates, List<WeatherDto> mWeather,
                             String mStations, WeatherDetailedMetricsDto mMainWeatherMetrics,
                             long mId, String mName, int mCode,
                             long mDate, LocationDto mLocationInfo) {
        this.mCoordinates = mCoordinates;
        this.mWeather = mWeather;
        this.mBase = mStations;
        this.mMainWeatherMetrics = mMainWeatherMetrics;
        this.mId = mId;
        this.mName = mName;
        this.mCode = mCode;
        this.mDate = mDate;
        this.mLocationInfo = mLocationInfo;
    }

    public CoordinatesDto getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(CoordinatesDto mCoordinates) {
        this.mCoordinates = mCoordinates;
    }

    public List<WeatherDto> getWeather() {
        return mWeather;
    }

    public void setWeather(List<WeatherDto> mWeather) {
        this.mWeather = mWeather;
    }

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        this.mBase = base;
    }

    public WeatherDetailedMetricsDto getMainWeatherMetrics() {
        return mMainWeatherMetrics;
    }

    public void setMainWeatherMetrics(WeatherDetailedMetricsDto mMainWeatherMetrics) {
        this.mMainWeatherMetrics = mMainWeatherMetrics;
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

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    public LocationDto getLocationInfo() {
        return mLocationInfo;
    }

    public void setLocationInfo(LocationDto mLocationInfo) {
        this.mLocationInfo = mLocationInfo;
    }
}

package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by muresand on 9/5/2017.
 */

public class WeatherForecastDto implements WeatherResponseMarker {

    @SerializedName("city")
    private CityInfoDto mLocation;

    @SerializedName("cnt")
    private int mDaysCount;

    @SerializedName("list")
    private ArrayList<WeatherForecastItemDto> mWeatherForecastItemsList;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(CityInfoDto mLocation, ArrayList<WeatherForecastItemDto> mWeatherForecastList) {
        this.mLocation = mLocation;
        this.mWeatherForecastItemsList = mWeatherForecastList;
    }

    public CityInfoDto getLocation() {
        return mLocation;
    }

    public void setLocation(CityInfoDto mLocation) {
        this.mLocation = mLocation;
    }

    public ArrayList<WeatherForecastItemDto> getWeatherForecastList() {
        return mWeatherForecastItemsList;
    }

    public void setWeatherForecastList(ArrayList<WeatherForecastItemDto> mWeatherForecastList) {
        this.mWeatherForecastItemsList = mWeatherForecastList;
    }

    public int getDaysCount() {
        return mDaysCount;
    }

    public void setDaysCount(int mDaysCount) {
        this.mDaysCount = mDaysCount;
    }
}

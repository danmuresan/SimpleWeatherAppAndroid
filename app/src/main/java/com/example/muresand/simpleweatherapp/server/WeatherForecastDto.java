package com.example.muresand.simpleweatherapp.server;

import java.util.ArrayList;

/**
 * Created by muresand on 9/5/2017.
 */

public class WeatherForecastDto {

    private LocationDto mLocation;
    private ArrayList<CurrentWeatherDto> mWeatherForecastList;

    public WeatherForecastDto(LocationDto mLocation, ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mLocation = mLocation;
        this.mWeatherForecastList = mWeatherForecastList;
    }

    public LocationDto getmLocation() {
        return mLocation;
    }

    public void setmLocation(LocationDto mLocation) {
        this.mLocation = mLocation;
    }

    public ArrayList<CurrentWeatherDto> getmWeatherForecastList() {
        return mWeatherForecastList;
    }

    public void setmWeatherForecastList(ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mWeatherForecastList = mWeatherForecastList;
    }
}

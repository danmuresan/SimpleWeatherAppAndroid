package com.example.muresand.simpleweatherapp.server;

import java.util.ArrayList;

/**
 * Created by muresand on 9/5/2017.
 */

public class WeatherForecastDto implements WeatherResponseMarker {

    private LocationDto mLocation;
    private ArrayList<CurrentWeatherDto> mWeatherForecastList;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(LocationDto mLocation, ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mLocation = mLocation;
        this.mWeatherForecastList = mWeatherForecastList;
    }

    public LocationDto getLocation() {
        return mLocation;
    }

    public void setLocation(LocationDto mLocation) {
        this.mLocation = mLocation;
    }

    public ArrayList<CurrentWeatherDto> getWeatherForecastList() {
        return mWeatherForecastList;
    }

    public void setWeatherForecastList(ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mWeatherForecastList = mWeatherForecastList;
    }
}

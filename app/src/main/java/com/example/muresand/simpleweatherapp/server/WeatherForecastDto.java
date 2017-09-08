package com.example.muresand.simpleweatherapp.server;

import java.util.ArrayList;

/**
 * Created by muresand on 9/5/2017.
 */

public class WeatherForecastDto implements WeatherResponseMarker {

    private CountryInfoDto mLocation;
    private ArrayList<CurrentWeatherDto> mWeatherForecastList;

    public WeatherForecastDto() {
    }

    public WeatherForecastDto(CountryInfoDto mLocation, ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mLocation = mLocation;
        this.mWeatherForecastList = mWeatherForecastList;
    }

    public CountryInfoDto getLocation() {
        return mLocation;
    }

    public void setLocation(CountryInfoDto mLocation) {
        this.mLocation = mLocation;
    }

    public ArrayList<CurrentWeatherDto> getWeatherForecastList() {
        return mWeatherForecastList;
    }

    public void setWeatherForecastList(ArrayList<CurrentWeatherDto> mWeatherForecastList) {
        this.mWeatherForecastList = mWeatherForecastList;
    }
}

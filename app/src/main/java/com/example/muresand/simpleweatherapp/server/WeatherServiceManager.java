package com.example.muresand.simpleweatherapp.server;

import com.example.muresand.simpleweatherapp.util.NetworkCallFailedException;

/**
 * Created by muresand on 9/5/2017.
 */

public interface WeatherServiceManager {

    void getCurrentWeather(long id, String appId, String units, WeatherApiResponseCallback<CurrentWeatherDto> currentWeatherCallback);

    void getCurrentWeatherByCoordinates(double latitude, double longitude, String appId, String units, WeatherApiResponseCallback<CurrentWeatherDto> currentWeatherCallback);

    void getWeatherForecast(long id, String appId, String units, int daysCount, WeatherApiResponseCallback<WeatherForecastDto> weatherForecastCallback);

}

package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/5/2017.
 */

public final class Constants {

    // TODO: ...
    public static final String AppId = "2a590cbc641090d4799ea2ea2e6b9f89";

    public static final String WeatherApiServerEnvironmentUri = "http://api.openweathermap.org/data/2.5/";

    public static final String CurrentWeatherEndpoint = "weather?id=%ld&appid=%s&units=%s";
    public static final String CurrentWeatherByCoordinatesEndpoint = "weather?lat=%lf&lon=%lf&appid=%s&units=%s";
    public static final String DailyWeatherForecastEndpoint = "forecast/daily?id=%ld&appid=%s&units=%s&cnt=%d";
    public static final String ImageForWeatherUri = "http://openweathermap.org/img/w/%s.png";

    private Constants() {
    }


}

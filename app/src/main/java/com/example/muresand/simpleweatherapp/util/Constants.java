package com.example.muresand.simpleweatherapp.util;

/**
 * Created by muresand on 9/5/2017.
 */

public final class Constants {

    public static final String AppName = "com.example.muresand.simpleweatherapp";

    public static final String AppId = "2a590cbc641090d4799ea2ea2e6b9f89";

    public static final String WeatherApiServerEnvironmentUri = "http://api.openweathermap.org/data/2.5/";

    public static final String CurrentWeatherEndpoint = "weather?id=%ld&appid=%s&units=%s";
    public static final String CurrentWeatherByCoordinatesEndpoint = "weather?lat=%lf&lon=%lf&appid=%s&units=%s";
    public static final String DailyWeatherForecastEndpoint = "forecast/daily?id=%ld&appid=%s&units=%s&cnt=%d";
    public static final String ImageForWeatherUri = "http://openweathermap.org/img/w/%s.png";

    public static final String KEY_CITY_NAME = "KeyCityName";
    public static final String KEY_CITY_ID = "KeyCityId";
    public static final String KEY_COUNTRY_NAME = "KeyCountryName";
    public static final String KEY_AUTO_DETECT_LOCATION_ENABLED = "KeyAutoDetectLocationEnabled";
    public static final String KEY_ANIMATIONS_ENABLED = "KeyAnimationsEnabled";
    public static final String KEY_UNIT_OF_MEASUREMENT = "KeyUnitOfMeasurement";
    public static final String KEY_NUMBER_OF_DAYS_IN_FORECAST = "KeyNumberOfDaysInForecast";
    public static final String KEY_LATITUDE = "KeyLatitude";
    public static final String KEY_LONGITUDE = "KeyLongitude";

    private Constants() {
    }
}

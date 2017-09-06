package com.example.muresand.simpleweatherapp.server;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("weather")
    Call<CurrentWeatherDto> getCurrentWeather(@Query("id") long id, @Query("appid") String appId, @Query("units") String units);

    @GET("weather")
    Call<CurrentWeatherDto> getCurrentWeatherByCoordinates(@Query("lat") double latitude, @Query("lon") double longitude, @Query("appid") String appId, @Query("units") String units);

    @GET("forecast/daily")
    Call<WeatherForecastDto> getWeatherForecast(@Query("id") long id, @Query("appid") String appId, @Query("units") String units, @Query("cnt") int daysCount);

}
package com.example.muresand.simpleweatherapp.server;

/**
 * Created by muresand on 9/6/2017.
 */

public interface WeatherApiResponseCallback<T extends WeatherResponseMarker> {

    void onSuccess(T responseDto);
    void onError(String message, int errorCode);

}
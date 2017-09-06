package com.example.muresand.simpleweatherapp.server;

import com.example.muresand.simpleweatherapp.util.NetworkCallFailedException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.muresand.simpleweatherapp.util.Constants.WeatherApiServerEnvironmentUri;

/**
 * Created by muresand on 9/5/2017.
 */

public class WeatherServiceManagerImpl implements WeatherServiceManager {

    private final Retrofit mRetrofit = new Retrofit
            .Builder()
            .baseUrl(WeatherApiServerEnvironmentUri)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private WeatherService mWeatherService;

    public WeatherServiceManagerImpl() {
        this.mWeatherService = mRetrofit.create(WeatherService.class);
    }

    @Override
    public void getCurrentWeather(long id, String appId, String units, final WeatherApiResponseCallback<CurrentWeatherDto> currentWeatherCallback) {

        Call<CurrentWeatherDto> currentWeatherDtoCall = mWeatherService.getCurrentWeather(id, appId, units);

        currentWeatherDtoCall.enqueue(new Callback<CurrentWeatherDto>() {
            @Override
            public void onResponse(Call<CurrentWeatherDto> call, Response<CurrentWeatherDto> response) {

                if (!response.isSuccessful()) {
                    currentWeatherCallback.onError(String.format("Network called failed. Check HTTP error code (%s)", response.errorBody()), response.code());
                }
                else {
                    currentWeatherCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherDto> call, Throwable t) {
                currentWeatherCallback.onError(t.getMessage(), -1);
            }
        });
    }

    @Override
    public void getCurrentWeatherByCoordinates(double latitude, double longitude, String appId, String units, final WeatherApiResponseCallback<CurrentWeatherDto> currentWeatherCallback) {

        Call<CurrentWeatherDto> currentWeatherDtoCall = mWeatherService.getCurrentWeatherByCoordinates(latitude, longitude, appId, units);

        currentWeatherDtoCall.enqueue(new Callback<CurrentWeatherDto>() {
            @Override
            public void onResponse(Call<CurrentWeatherDto> call, Response<CurrentWeatherDto> response) {

                if (!response.isSuccessful()) {
                    currentWeatherCallback.onError(String.format("Network called failed. Check HTTP error code (%s)", response.errorBody()), response.code());
                }
                else {
                    currentWeatherCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherDto> call, Throwable t) {
                currentWeatherCallback.onError(t.getMessage(), -1);
            }
        });
    }

    @Override
    public void getWeatherForecast(long id, String appId, String units, int daysCount, final WeatherApiResponseCallback<WeatherForecastDto> weatherForecastCallback) {

        Call<WeatherForecastDto> weatherForecastCall = mWeatherService.getWeatherForecast(id, appId, units, daysCount);

        weatherForecastCall.enqueue(new Callback<WeatherForecastDto>() {
            @Override
            public void onResponse(Call<WeatherForecastDto> call, Response<WeatherForecastDto> response) {

                if (!response.isSuccessful()) {
                    weatherForecastCallback.onError(String.format("Network called failed. Check HTTP error code (%s)", response.errorBody()), response.code());
                }
                else {
                    weatherForecastCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherForecastDto> call, Throwable t) {
                weatherForecastCallback.onError(t.getMessage(), -1);
            }
        });
    }
}

package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by muresand on 9/26/2017.
 */

public class WeatherForecastItemDto {

    @SerializedName("dt")
    private long mDate;

    @SerializedName("temp")
    private WeatherForecastItemTemperatureDetailsDto mWeatherForecastItemTemperatureDetailsDto;

    @SerializedName("pressure")
    private double mPressure;

    @SerializedName("humidity")
    private double mHumidity;

    @SerializedName("weather")
    private List<WeatherDto> mWeather;

    @SerializedName("speed")
    private double mSpeed;

    @SerializedName("clouds")
    private int mClouds;

    public WeatherForecastItemDto() {
    }

    public WeatherForecastItemDto(long mDate, WeatherForecastItemTemperatureDetailsDto mWeatherForecastItemTemperatureDetailsDto, double mPressure, double mHumidity, List<WeatherDto> mWeather, double mSpeed, int mClouds) {
        this.mDate = mDate;
        this.mWeatherForecastItemTemperatureDetailsDto = mWeatherForecastItemTemperatureDetailsDto;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        this.mWeather = mWeather;
        this.mSpeed = mSpeed;
        this.mClouds = mClouds;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long mDate) {
        this.mDate = mDate;
    }

    public WeatherForecastItemTemperatureDetailsDto getWeatherForecastItemTemperatureDetailsDto() {
        return mWeatherForecastItemTemperatureDetailsDto;
    }

    public void setWeatherForecastItemTemperatureDetailsDto(WeatherForecastItemTemperatureDetailsDto mWeatherForecastItemTemperatureDetailsDto) {
        this.mWeatherForecastItemTemperatureDetailsDto = mWeatherForecastItemTemperatureDetailsDto;
    }

    public double getPressure() {
        return mPressure;
    }

    public void setPressure(double mPressure) {
        this.mPressure = mPressure;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public List<WeatherDto> getWeather() {
        return mWeather;
    }

    public void setWeather(List<WeatherDto> mWeather) {
        this.mWeather = mWeather;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double mSpeed) {
        this.mSpeed = mSpeed;
    }

    public int getClouds() {
        return mClouds;
    }

    public void setClouds(int mClouds) {
        this.mClouds = mClouds;
    }
}

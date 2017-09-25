package com.example.muresand.simpleweatherapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.server.CoordinatesDto;
import com.example.muresand.simpleweatherapp.server.CurrentWeatherDto;
import com.example.muresand.simpleweatherapp.server.WeatherApiResponseCallback;
import com.example.muresand.simpleweatherapp.server.WeatherDto;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManager;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManagerImpl;
import com.example.muresand.simpleweatherapp.util.AppSettingsUtil;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.DownloadImageAsyncTask;
import com.example.muresand.simpleweatherapp.util.UnitOfMeasurement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentWeatherFragment extends Fragment {

    private WeatherServiceManager mWeatherServiceManager;

    private ProgressBar mainProgressSpinner;
    private TextView mTemperatureTextView;
    private TextView mCityTextView;
    private TextView mWeatherDescriptionTextView;
    private TextView mWeatherUpdatedDateTimeDescription;
    private TextView mUnitOfMeasurementTextView;
    private ImageView mWeatherIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.fragment_current_weather, container, false);

        mainProgressSpinner = (ProgressBar) currentView.findViewById(R.id.mainProgressSpinner);
        mCityTextView = (TextView) currentView.findViewById(R.id.cityTextView);
        mTemperatureTextView = (TextView) currentView.findViewById(R.id.temperatureTextView);
        mWeatherUpdatedDateTimeDescription = (TextView) currentView.findViewById(R.id.weatherUpdateDateTimeTextView);
        mUnitOfMeasurementTextView = (TextView) currentView.findViewById(R.id.degreesTextView);
        mWeatherDescriptionTextView = (TextView) currentView.findViewById(R.id.weatherDescriptionTextView);
        mWeatherIcon = (ImageView) currentView.findViewById(R.id.weatherIcon);

        CoordinatesDto locationByCoords = AppSettingsUtil.loadLocationCoordinatesSettings(getContext());
        if (locationByCoords == null || (locationByCoords.getLatitude() == 0 && locationByCoords.getLongitude() == 0))
        {
            locationByCoords = new CoordinatesDto(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE, "Cluj-Napoca");
        }

        mWeatherServiceManager = new WeatherServiceManagerImpl();
        getWeatherDataByCoordinates(locationByCoords.getLatitude(), locationByCoords.getLongitude(), locationByCoords.getCityName());
        return currentView;
    }

    public void refreshData() {
        // TODO: update
        CoordinatesDto locationByCoords = AppSettingsUtil.loadLocationCoordinatesSettings(getContext());
        if (locationByCoords == null || (locationByCoords.getLatitude() == 0 && locationByCoords.getLongitude() == 0))
        {
            locationByCoords = new CoordinatesDto(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE, "Cluj-Napoca");
        }

        getWeatherDataByCoordinates(locationByCoords.getLatitude(), locationByCoords.getLongitude(), locationByCoords.getCityName());
    }

    private void getWeatherDataByCoordinates(double latitude, double longitude, final String cityName) {

        // start up the spinner
        mainProgressSpinner.setVisibility(View.VISIBLE);

        try {
            mWeatherServiceManager.getCurrentWeatherByCoordinates(latitude, longitude, Constants.AppId, UnitOfMeasurement.METRIC.getName(), new WeatherApiResponseCallback<CurrentWeatherDto>() {
                @Override
                public void onSuccess(CurrentWeatherDto responseDto) {

                    WeatherDto weatherDescription = responseDto.getWeather().get(0);
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    try {
                        Date weatherUpdatedDate = new Date(responseDto.getDate() * 1000);
                        mWeatherUpdatedDateTimeDescription.setText(weatherUpdatedDate.toString());
                    } catch (Exception ex) {
                        Log.d("MAIN ACTIVITY", "Couldn't set weather updated date!");
                    }

                    mCityTextView.setText(cityName);
                    mTemperatureTextView.setText(Double.toString(responseDto.getMainWeatherMetrics().getTempMain()) + (char) 0x00B0);
                    mWeatherDescriptionTextView.setText(weatherDescription.getDescription());
                    updateWeatherIcon(weatherDescription.getIcon());

                    mainProgressSpinner.setVisibility(View.GONE);
                }

                @Override
                public void onError(String message, int errorCode) {

                    mCityTextView.setText(String.format("Network call failed with message: %s (code: %d)", message, errorCode));
                    mainProgressSpinner.setVisibility(View.GONE);
                }
            });

        } catch (Exception ex) {
            Log.e("MAIN_ACTIVITY", ex.getMessage());
        }
    }

    private void updateWeatherIcon(String imageName) {
        final String fullImageUrl = String.format(Constants.ImageForWeatherUri, imageName);
        new DownloadImageAsyncTask(mWeatherIcon).execute(fullImageUrl);
    }
}
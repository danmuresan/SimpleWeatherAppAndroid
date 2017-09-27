package com.example.muresand.simpleweatherapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.server.CurrentWeatherDto;
import com.example.muresand.simpleweatherapp.server.WeatherApiResponseCallback;
import com.example.muresand.simpleweatherapp.server.WeatherForecastDto;
import com.example.muresand.simpleweatherapp.server.WeatherForecastItemDto;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManager;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManagerImpl;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.UnitOfMeasurement;
import com.example.muresand.simpleweatherapp.util.WeatherItemModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherForecastFragment extends WeatherRetrievingFragmentBase {

    private ListView mWeatherForecastListView;
    private ProgressBar mForecastProgressSpinner;
    private TextView mHeaderTextView;
    private WeatherServiceManager mWeatherServiceManager;
    private WeatherForecastArrayAdapter mWeatherForecastArrayAdapter;

    public WeatherForecastFragment() {
        // Required empty public constructor
        mWeatherServiceManager = new WeatherServiceManagerImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View currentView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);
        mWeatherForecastListView = currentView.findViewById(R.id.weatherForecastListView);
        mForecastProgressSpinner = currentView.findViewById(R.id.forecastProgressSpinner);
        mHeaderTextView = currentView.findViewById(R.id.forecastHeaderTextView);

        getWeatherForecast(Constants.DEFAULT_CITY_ID, mGeneralSettings.getNumberOfDaysInForecast());
        return currentView;
    }

    @Override
    public void refreshData() {
        super.refreshData();
        getWeatherForecast(Constants.DEFAULT_CITY_ID, mGeneralSettings.getNumberOfDaysInForecast());
    }

    private void getWeatherForecast(long cityId, int numberOfDays) {
        mForecastProgressSpinner.setVisibility(View.VISIBLE);
        mHeaderTextView.setText(String.format("Daily Forecast - %d days", mGeneralSettings.getNumberOfDaysInForecast()));

        mWeatherServiceManager.getWeatherForecast(cityId, Constants.AppId, mGeneralSettings.getUnitOfMeasurement().getName(), numberOfDays, new WeatherApiResponseCallback<WeatherForecastDto>() {
            @Override
            public void onSuccess(WeatherForecastDto responseDto) {

                if (responseDto != null && responseDto.getWeatherForecastList() != null && responseDto.getLocation() != null) {
                    ArrayList<WeatherItemModel> weatherItemList = new ArrayList<WeatherItemModel>();
                    for (WeatherForecastItemDto currentWeatherItem : responseDto.getWeatherForecastList()) {
                        WeatherItemModel listItem = new WeatherItemModel();

                        listItem.setDescription(currentWeatherItem.getWeather().get(0).getDescription());
                        listItem.setCityName(responseDto.getLocation().getName());
                        listItem.setDegrees(currentWeatherItem.getWeatherForecastItemTemperatureDetailsDto().getMaxTemp());
                        listItem.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentWeatherItem.getDate() * 1000)));
                        listItem.setUnitOfMeasurement(mGeneralSettings.getUnitOfMeasurement());
                        listItem.setIconSource(currentWeatherItem.getWeather().get(0).getIcon());

                        weatherItemList.add(listItem);
                    }

                    mWeatherForecastArrayAdapter = new WeatherForecastArrayAdapter(getContext(), weatherItemList, mGeneralSettings.isAnimationsEnabled());
                    mWeatherForecastListView.setAdapter(mWeatherForecastArrayAdapter);
                }

                mForecastProgressSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onError(String message, int errorCode) {

                // TODO: show error label
                mForecastProgressSpinner.setVisibility(View.GONE);
            }
        });
    }

}

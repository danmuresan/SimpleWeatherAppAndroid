package com.example.muresand.simpleweatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.Spanned;
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
import com.example.muresand.simpleweatherapp.util.AnimationsUtil;
import com.example.muresand.simpleweatherapp.util.AppSettingsUtil;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.DownloadImageAsyncTask;
import com.example.muresand.simpleweatherapp.util.GeneralSettingsModel;
import com.example.muresand.simpleweatherapp.util.LocationModel;
import com.example.muresand.simpleweatherapp.util.UnitOfMeasurement;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentWeatherFragment extends WeatherRetrievingFragmentBase {

    private WeatherServiceManager mWeatherServiceManager;

    private ProgressBar mainProgressSpinner;
    private TextView mTemperatureTextView;
    private TextView mCityTextView;
    private TextView mWeatherDescriptionTextView;
    private TextView mWeatherUpdatedDateTimeDescription;
    private TextView mUnitOfMeasurementTextView;
    private ImageView mWeatherIcon;
    private TextView mWeatherHumidityTextView;
    private TextView mWeatherPressureTextView;
    private TextView mMinTempTextView;
    private TextView mMaxTempTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View currentView = inflater.inflate(R.layout.fragment_current_weather, container, false);

        mainProgressSpinner = currentView.findViewById(R.id.mainProgressSpinner);
        mCityTextView = currentView.findViewById(R.id.cityTextView);
        mTemperatureTextView = currentView.findViewById(R.id.temperatureTextView);
        mWeatherUpdatedDateTimeDescription = currentView.findViewById(R.id.weatherUpdateDateTimeTextView);
        mUnitOfMeasurementTextView = currentView.findViewById(R.id.degreesTextView);
        mWeatherDescriptionTextView = currentView.findViewById(R.id.weatherDescriptionTextView);
        mWeatherIcon = currentView.findViewById(R.id.weatherIcon);
        mWeatherHumidityTextView = currentView.findViewById(R.id.weatherHumidityPercentageTextView);
        mWeatherPressureTextView = currentView.findViewById(R.id.weatherPressureTextView);
        mMaxTempTextView = currentView.findViewById(R.id.weatherMaxTempTextView);
        mMinTempTextView = currentView.findViewById(R.id.weatherMinTempTextView);

        LocationModel locationModel = AppSettingsUtil.loadLocationSettings(getContext());
        if (locationModel == null || locationModel.getCoordinates() == null || (locationModel.getCoordinates().getLatitude() == 0 && locationModel.getCoordinates().getLongitude() == 0))
        {
            locationModel = new LocationModel();
            locationModel.setCoordinates(new CoordinatesDto(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE));
            locationModel.setCity("Cluj-Napoca");
            locationModel.setCountry("RO");
        }

        mWeatherServiceManager = new WeatherServiceManagerImpl();
        getWeatherData(locationModel);
        return currentView;
    }

    @Override
    public void refreshData() {

        super.refreshData();
        LocationModel locationModel = AppSettingsUtil.loadLocationSettings(getContext());
        if (locationModel == null || locationModel.getCoordinates() == null || (locationModel.getCoordinates().getLatitude() == 0 && locationModel.getCoordinates().getLongitude() == 0))
        {
            locationModel = new LocationModel();
            locationModel.setCoordinates(new CoordinatesDto(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE));
            locationModel.setCity("Cluj-Napoca");
            locationModel.setCountry("RO");
        }

        getWeatherData(locationModel);
    }

    private void getWeatherData(final LocationModel locationModel) {

        double latitude = locationModel.getCoordinates().getLatitude();
        double longitude = locationModel.getCoordinates().getLongitude();

        // start up the spinner
        mainProgressSpinner.setVisibility(View.VISIBLE);

        try {
            mWeatherServiceManager.getCurrentWeatherByCoordinates(latitude, longitude, Constants.AppId, mGeneralSettings.getUnitOfMeasurement().getName(), new WeatherApiResponseCallback<CurrentWeatherDto>() {
                @Override
                public void onSuccess(CurrentWeatherDto responseDto) {

                    WeatherDto weatherDescription = responseDto.getWeather().get(0);
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                    try {
                        Date weatherUpdatedDate = new Date(responseDto.getDate() * 1000);
                        mWeatherUpdatedDateTimeDescription.setText(dt.format(weatherUpdatedDate));
                    } catch (Exception ex) {
                        Log.d("MAIN ACTIVITY", "Couldn't set weather updated date!");
                    }

                    mCityTextView.setText(locationModel.getCity() + ", " + responseDto.getLocationInfo().getCountry());
                    mTemperatureTextView.setText(String.format("%.1f", responseDto.getMainWeatherMetrics().getTempMain()) + (char) 0x00B0);
                    mWeatherDescriptionTextView.setText(weatherDescription.getDescription());
                    mUnitOfMeasurementTextView.setText(mGeneralSettings.getUnitOfMeasurement().getAppropriateDegreeUnit());
                    updateWeatherIcon(weatherDescription.getIcon());
                    mWeatherHumidityTextView.setText(formatStringWithHtmlTags(String.format("<b>Humidity:</b>  %d%%", responseDto.getMainWeatherMetrics().getHumidityPercentage())));
                    mMinTempTextView.setText(formatStringWithHtmlTags(String.format("<b>Min Temp:</b>  %.1f", responseDto.getMainWeatherMetrics().getMinTemperature()) + (char) 0x00B0 + " " + mGeneralSettings.getUnitOfMeasurement().getAppropriateDegreeUnit()));
                    mMaxTempTextView.setText(formatStringWithHtmlTags(String.format("<b>Max Temp:</b>  %.1f", responseDto.getMainWeatherMetrics().getMaxTemperature()) + (char) 0x00B0 + " " + mGeneralSettings.getUnitOfMeasurement().getAppropriateDegreeUnit()));
                    mWeatherPressureTextView.setText(formatStringWithHtmlTags(String.format("<b>Pressure:</b> %.1f mbar", responseDto.getMainWeatherMetrics().getPressure())));

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

    private Spanned formatStringWithHtmlTags(String rawString) {
        return Html.fromHtml(rawString);
    }

    private void updateWeatherIcon(String imageName) {
        final String fullImageUrl = String.format(Constants.ImageForWeatherUri, imageName);
        new DownloadImageAsyncTask(mWeatherIcon).execute(fullImageUrl);

        if (mGeneralSettings.isAnimationsEnabled())
        {
            AnimationsUtil.animateImageViewByScaling(mWeatherIcon, 1.0f);
        }
    }
}
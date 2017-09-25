package com.example.muresand.simpleweatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.muresand.simpleweatherapp.server.CoordinatesDto;

import static com.example.muresand.simpleweatherapp.util.Constants.KEY_ANIMATIONS_ENABLED;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_AUTO_DETECT_LOCATION_ENABLED;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_CITY_ID;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_CITY_NAME;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_COUNTRY_NAME;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_LATITUDE;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_LONGITUDE;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_NUMBER_OF_DAYS_IN_FORECAST;
import static com.example.muresand.simpleweatherapp.util.Constants.KEY_UNIT_OF_MEASUREMENT;

/**
 * Created by muresand on 9/7/2017.
 */

public class AppSettingsUtil {

    private AppSettingsUtil() {}

    public static void saveGeneralSettings(Context context, GeneralSettingsModel generalSettingsModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.AppName, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();

        // save general settings individually
        sharedPreferenceEditor.putString(KEY_UNIT_OF_MEASUREMENT, generalSettingsModel.getUnitOfMeasurement().getName());
        sharedPreferenceEditor.putBoolean(KEY_AUTO_DETECT_LOCATION_ENABLED, generalSettingsModel.isAutoDetectLocation());
        sharedPreferenceEditor.putBoolean(KEY_ANIMATIONS_ENABLED, generalSettingsModel.isAnimationsEnabled());
        sharedPreferenceEditor.putInt(KEY_NUMBER_OF_DAYS_IN_FORECAST, generalSettingsModel.getNumberOfDaysInForecast());

        // check and save inner location related settings
        LocationModel locationData = generalSettingsModel.getLocationModel();
        if (locationData != null) {
            sharedPreferenceEditor.putString(KEY_CITY_NAME, locationData.getCity());
            sharedPreferenceEditor.putLong(KEY_CITY_ID, locationData.getCityId());
            sharedPreferenceEditor.putString(KEY_COUNTRY_NAME, locationData.getCountry());

            // check and save coords if necessary
            CoordinatesDto coordinatesData = locationData.getCoordinates();
            if (coordinatesData != null) {
                sharedPreferenceEditor.putLong(KEY_LATITUDE, Double.doubleToRawLongBits(coordinatesData.getLatitude()));
                sharedPreferenceEditor.putLong(KEY_LONGITUDE, Double.doubleToRawLongBits(coordinatesData.getLongitude()));
            }
        }

        // save all changes
        sharedPreferenceEditor.commit();
    }

    public static GeneralSettingsModel loadGeneralSettings(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.AppName, Context.MODE_PRIVATE);

        // get coordinates data
        double latitude = Double.longBitsToDouble(sharedPreferences.getLong(KEY_LATITUDE, Double.doubleToLongBits(0)));
        double longitude =  Double.longBitsToDouble(sharedPreferences.getLong(KEY_LONGITUDE, Double.doubleToLongBits(0)));
        CoordinatesDto coords = new CoordinatesDto(latitude, longitude);

        // get location data
        String cityName = sharedPreferences.getString(KEY_CITY_NAME, "");
        String countryName = sharedPreferences.getString(KEY_COUNTRY_NAME, "");
        long cityId = sharedPreferences.getLong(KEY_CITY_ID, 0);
        LocationModel location = new LocationModel(coords, cityId, cityName, countryName);

        // get rest of the data
        UnitOfMeasurement unitOfMeasurement = UnitOfMeasurement.getEnumValueFromName(sharedPreferences.getString(KEY_UNIT_OF_MEASUREMENT, "metric"));
        boolean autoDetectLocation = sharedPreferences.getBoolean(KEY_AUTO_DETECT_LOCATION_ENABLED, false);
        boolean animationsEnabled = sharedPreferences.getBoolean(KEY_ANIMATIONS_ENABLED, false);
        int numberOfDaysInForecast = sharedPreferences.getInt(KEY_NUMBER_OF_DAYS_IN_FORECAST, 5);
        GeneralSettingsModel settings = new GeneralSettingsModel(autoDetectLocation, animationsEnabled, unitOfMeasurement, numberOfDaysInForecast, location);

        return settings;
    }

    public static void saveLocationCoordinatesSettings(Context context, CoordinatesDto coordinatesData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.AppName, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();

        sharedPreferenceEditor.putString(KEY_CITY_NAME, coordinatesData.getCityName());
        sharedPreferenceEditor.putLong(KEY_LATITUDE, Double.doubleToRawLongBits(coordinatesData.getLatitude()));
        sharedPreferenceEditor.putLong(KEY_LONGITUDE, Double.doubleToRawLongBits(coordinatesData.getLongitude()));

        sharedPreferenceEditor.commit();
    }

    public static CoordinatesDto loadLocationCoordinatesSettings(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.AppName, Context.MODE_PRIVATE);

        String cityName = sharedPreferences.getString(KEY_CITY_NAME, "Unknown");
        double latitude = Double.longBitsToDouble(sharedPreferences.getLong(KEY_LATITUDE, Double.doubleToLongBits(0)));
        double longitude =  Double.longBitsToDouble(sharedPreferences.getLong(KEY_LONGITUDE, Double.doubleToLongBits(0)));
        CoordinatesDto coords = new CoordinatesDto(latitude, longitude, cityName);

        return coords;
    }
}

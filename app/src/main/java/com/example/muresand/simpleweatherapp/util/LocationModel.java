package com.example.muresand.simpleweatherapp.util;

import android.content.Context;
import android.location.Location;

import com.example.muresand.simpleweatherapp.server.CoordinatesDto;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by muresand on 9/7/2017.
 */

public class LocationModel {

    private CoordinatesDto mCoordinates;
    private long mCityId;
    private String mCity;
    private String mCountry;

    public LocationModel() {
    }

    public LocationModel(CoordinatesDto mCoordinates, long mCityId, String mCity, String mCountry) {
        this.mCoordinates = mCoordinates;
        this.mCityId = mCityId;
        this.mCity = mCity;
        this.mCountry = mCountry;
    }

    public static LocationModel fromAndroidNativeLocation(Location nativeLocation, Context ctx) {
        LocationModel newLocationByCoords = new LocationModel();
        LatLng coords;
        if (nativeLocation == null)
        {
            coords = new LatLng(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE);
        }
        else
        {
            coords = new LatLng(nativeLocation.getLatitude(), nativeLocation.getLongitude());
        }


        newLocationByCoords.setCoordinates(new CoordinatesDto(coords.latitude, coords.longitude));
        newLocationByCoords.setCity(LocationHelper.getCityFromCoordinates(ctx, newLocationByCoords.getCoordinates().getLatitude(), newLocationByCoords.getCoordinates().getLongitude()));
        newLocationByCoords.setCountry(LocationHelper.getCountryFromCoordinates(ctx, newLocationByCoords.getCoordinates().getLatitude(), newLocationByCoords.getCoordinates().getLongitude()));

        // TODO: find city ID as well (if possible)

        return newLocationByCoords;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (otherObj instanceof LocationModel) {
            LocationModel other = (LocationModel)otherObj;
            return  other.getCountry().equals(mCountry) &&
                    other.getCity().equals(mCity) &&
                    other.getCoordinates().equals(mCoordinates);
        }

        return false;
    }

    public CoordinatesDto getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(CoordinatesDto mCoordinates) {
        this.mCoordinates = mCoordinates;
    }

    public long getCityId() {
        return mCityId;
    }

    public void setCityId(long mCityId) {
        this.mCityId = mCityId;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }
}

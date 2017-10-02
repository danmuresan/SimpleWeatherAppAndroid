package com.example.muresand.simpleweatherapp.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.List;
import java.util.Locale;

/**
 * Created by muresand on 10/2/2017.
 */

public class LocationHelper {

    private LocationHelper() {
    }

    public static Location getLastKnownBestLocation(Context context) {

        if (context == null) {
            return null;
        }

        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager == null) {
                return null;
            }

            List<String> providers = locationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }
        catch (Exception e) {
            Log.d("LOCATION_HELPER", e.getMessage());
            return null;
        }
    }

    public static String getCityFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
        }
        catch (Exception ex) {
            return "Unknown";
        }

        if (addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        else {
            return "Unknown";
        }
    }

    public static String getCountryFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());

        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
        }
        catch (Exception ex) {
            return "Unknown";
        }

        if (addresses.size() > 0) {
            return addresses.get(0).getCountryCode();
        }
        else {
            return "Unknown";
        }
    }

}

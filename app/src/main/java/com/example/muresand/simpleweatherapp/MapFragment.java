package com.example.muresand.simpleweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.muresand.simpleweatherapp.server.CoordinatesDto;
import com.example.muresand.simpleweatherapp.util.AppSettingsUtil;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.LocationHelper;
import com.example.muresand.simpleweatherapp.util.LocationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends WeatherRetrievingFragmentBase {

    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private Button mapLocationSaveBtn;
    private LatLng mSelectedLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View currentView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) currentView.findViewById(R.id.mapView);
        mapLocationSaveBtn = (Button) currentView.findViewById(R.id.mapLocationSaveBtn);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED) {

                    // For showing a move to my location button
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[] {
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION },
                                    0);
                }

                LatLng lastKnownLocationCoords = getAppropriateLocationToZoomInto();

                // zoom into last known location
                CameraPosition cameraPosition = new CameraPosition.Builder().target(lastKnownLocationCoords).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(latLng));
                        mSelectedLocation = latLng;
                    }
                });

                mGoogleMap = googleMap;
            }
        });

        mapLocationSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mSelectedLocation == null)
                    {
                        Toast.makeText(getContext(), "Please select a location from the map and save it using this button!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    LocationModel newLocationByCoords = new LocationModel();
                    newLocationByCoords.setCoordinates(new CoordinatesDto(mSelectedLocation.latitude, mSelectedLocation.longitude));
                    newLocationByCoords.setCity(LocationHelper.getCityFromCoordinates(getContext(), newLocationByCoords.getCoordinates().getLatitude(), newLocationByCoords.getCoordinates().getLongitude()));
                    newLocationByCoords.setCountry(LocationHelper.getCountryFromCoordinates(getContext(), newLocationByCoords.getCoordinates().getLatitude(), newLocationByCoords.getCoordinates().getLongitude()));

                    AppSettingsUtil.saveLocationSettings(getContext(), newLocationByCoords);

                    mGeneralSettings.setAutoDetectLocation(false);
                    AppSettingsUtil.saveGeneralSettings(getContext(), mGeneralSettings);

                    Intent intent = new Intent("settings-changed-event");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

                    Toast.makeText(getContext(), "Location saved successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Failed saving selected location!", Toast.LENGTH_SHORT).show();
                    Log.d("MAP_FRAGMENT", "Failed to save location coords!");
                }
            }
        });

        return currentView;
    }

    private LatLng getAppropriateLocationToZoomInto() {
        LatLng lastKnownLocationCoords;
        if (mGeneralSettings == null || mGeneralSettings.getLocationModel() == null || mGeneralSettings.isAutoDetectLocation()) {

            Location lastKnownLocation = LocationHelper.getLastKnownBestLocation(getContext());
            if (lastKnownLocation == null)
            {
                lastKnownLocationCoords = new LatLng(Constants.DEFAULT_LOCATION_LATITUDE, Constants.DEFAULT_LOCATION_LONGITUDE);
            }
            else
            {
                lastKnownLocationCoords = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            }
        }
        else {
            lastKnownLocationCoords = new LatLng(mGeneralSettings.getLocationModel().getCoordinates().getLatitude(), mGeneralSettings.getLocationModel().getCoordinates().getLongitude());
        }

        return lastKnownLocationCoords;
    }

    @Override
    public void refreshData() {
        super.refreshData();

        LatLng lastKnownLocationCoords = getAppropriateLocationToZoomInto();

        // zoom into last known location
        CameraPosition cameraPosition = new CameraPosition.Builder().target(lastKnownLocationCoords).zoom(12).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

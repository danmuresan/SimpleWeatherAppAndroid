package com.example.muresand.simpleweatherapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.server.CurrentWeatherDto;
import com.example.muresand.simpleweatherapp.server.WeatherApiResponseCallback;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManager;
import com.example.muresand.simpleweatherapp.server.WeatherServiceManagerImpl;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.UnitOfMeasurement;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WeatherServiceManager mWeatherServiceManager;

    private ProgressBar mainProgressSpinner;
    private TextView mTemperatureTextView;
    private TextView mCityTextView;
    private TextView mWeatherDescriptionTextView;
    private TextView mWeatherUpdatedDateTimeDescription;
    private TextView mUnitOfMeasurementTextView;
    private ImageView mWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mWeatherServiceManager = new WeatherServiceManagerImpl();

        // get items
        mainProgressSpinner = (ProgressBar) findViewById(R.id.mainProgressSpinner);
        mCityTextView = (TextView) findViewById(R.id.cityTextView);
        mTemperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        mWeatherUpdatedDateTimeDescription = (TextView) findViewById(R.id.weatherUpdateDateTimeTextView);
        mUnitOfMeasurementTextView = (TextView) findViewById(R.id.degreesTextView);
        mWeatherDescriptionTextView = (TextView) findViewById(R.id.weatherDescriptionTextView);
        mWeatherIcon = (ImageView) findViewById(R.id.weatherIcon);

        // start up the spinner
        mainProgressSpinner.setVisibility(View.VISIBLE);

        try {
            mWeatherServiceManager.getCurrentWeatherByCoordinates(46.771210, 23.623635, Constants.AppId, UnitOfMeasurement.METRIC.getName(), new WeatherApiResponseCallback<CurrentWeatherDto>() {
                @Override
                public void onSuccess(CurrentWeatherDto responseDto) {


                    mCityTextView.setText(responseDto.getLocationInfo().getCountry());
                    mTemperatureTextView.setText(Double.toString(responseDto.getMainWeatherMetrics().getTempMain()));
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorite_location1) {
            // Handle the camera action
        } else if (id == R.id.nav_favorite_location2) {

        } else if (id == R.id.nav_favorite_location3) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_help_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

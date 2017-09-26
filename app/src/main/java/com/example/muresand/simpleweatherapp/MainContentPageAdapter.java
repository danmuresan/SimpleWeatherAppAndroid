package com.example.muresand.simpleweatherapp;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by muresand on 9/25/2017.
 */

public class MainContentPageAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private CurrentWeatherFragment mCurrentWeatherFragment;
    private MapFragment mMapFragment;
    private WeatherForecastFragment mWeatherForecastFragment;

    public MainContentPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (mCurrentWeatherFragment == null)
                {
                    mCurrentWeatherFragment = new CurrentWeatherFragment();
                }

                return mCurrentWeatherFragment;
            case 1:
                if (mWeatherForecastFragment == null)
                {
                    mWeatherForecastFragment = new WeatherForecastFragment();
                }

                return mWeatherForecastFragment;
            case 2:
                if (mMapFragment == null)
                {
                    mMapFragment = new MapFragment();
                }

                return mMapFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void refreshItemData(int position) {
        switch (position) {
            case 0:
                mCurrentWeatherFragment.refreshData();
            case 1:
                mWeatherForecastFragment.refreshData();
        }
    }

}

package com.example.muresand.simpleweatherapp;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muresand.simpleweatherapp.util.AppSettingsUtil;
import com.example.muresand.simpleweatherapp.util.GeneralSettingsModel;

/**
 * Created by muresand on 9/26/2017.
 */

public class WeatherRetrievingFragmentBase extends android.support.v4.app.Fragment {

    protected GeneralSettingsModel mGeneralSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mGeneralSettings = AppSettingsUtil.loadGeneralSettings(getContext());
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mSettingsChangedReceiver,
                new IntentFilter("settings-changed-event"));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void refreshData() {
        mGeneralSettings = AppSettingsUtil.loadGeneralSettings(getContext());
    }

    protected BroadcastReceiver mSettingsChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshData();
        }
    };

}

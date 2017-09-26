package com.example.muresand.simpleweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.util.AnimationsUtil;
import com.example.muresand.simpleweatherapp.util.Constants;
import com.example.muresand.simpleweatherapp.util.DownloadImageAsyncTask;
import com.example.muresand.simpleweatherapp.util.WeatherItemModel;

import java.util.ArrayList;

/**
 * Created by muresand on 9/26/2017.
 */

public class WeatherForecastArrayAdapter extends ArrayAdapter<WeatherItemModel> {

    private final Context mContext;
    private final ArrayList<WeatherItemModel> mWeatherItemList;

    private boolean mAnimationsEnabled;

    public WeatherForecastArrayAdapter(Context context, ArrayList<WeatherItemModel> itemsList, boolean animationsEnabled) {
        super(context, R.layout.weather_forecast_row, itemsList);

        mContext = context;
        mWeatherItemList = itemsList;
        mAnimationsEnabled = animationsEnabled;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View rowView = inflater.inflate(R.layout.weather_forecast_row, parent, false);
        WeatherItemModel currentItem = mWeatherItemList.get(position);

        TextView descriptionTextView = rowView.findViewById(R.id.weatherForecastRowDescriptionTextView);
        descriptionTextView.setText(currentItem.getDescription());

        TextView tempTextView = rowView.findViewById(R.id.weatherForecastRowTempTextView);
        tempTextView.setText(Double.toString(currentItem.getDegrees()) + (char) 0x00B0 + " " + currentItem.getUnitOfMeasurement().getAppropriateDegreeUnit());

        TextView dateTextView = rowView.findViewById(R.id.weatherForecastRowDateTextView);
        dateTextView.setText(currentItem.getDate());

        ImageView weatherIcon = rowView.findViewById(R.id.weatherForecastRowImageView);
        updateWeatherIcon(currentItem.getIconSource(), weatherIcon);

        return rowView;
    }

    private  void updateWeatherIcon(String imageUri, ImageView weatherIcon) {
        final String fullImageUrl = String.format(Constants.ImageForWeatherUri, imageUri);
        new DownloadImageAsyncTask(weatherIcon).execute(fullImageUrl);

        if (mAnimationsEnabled) {
            AnimationsUtil.animateImageViewWithRotation(weatherIcon, 360f);
        }
    }
}

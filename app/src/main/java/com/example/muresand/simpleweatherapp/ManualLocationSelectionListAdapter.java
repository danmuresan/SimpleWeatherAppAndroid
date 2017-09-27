package com.example.muresand.simpleweatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muresand.simpleweatherapp.util.LocationModel;
import com.example.muresand.simpleweatherapp.util.WeatherItemModel;

import java.util.ArrayList;

/**
 * Created by muresand on 9/27/2017.
 */

public class ManualLocationSelectionListAdapter extends ArrayAdapter<LocationModel> {

    private final Context mContext;
    private ArrayList<LocationModel> mLocationsList;

    public ManualLocationSelectionListAdapter(Context context, ArrayList<LocationModel> itemsList) {
        super(context, R.layout.pref_manual_location_list_row, itemsList);

        mContext = context;
        mLocationsList = itemsList;
    }

    public ArrayList<LocationModel> getLocationsList() {
        return mLocationsList;
    }

    public void setLocationsList(ArrayList<LocationModel> locationsList) {
        this.mLocationsList = locationsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View rowView = inflater.inflate(R.layout.pref_manual_location_list_row, parent, false);
        LocationModel currentItem = mLocationsList.get(position);

        TextView locationTextView = rowView.findViewById(R.id.manualLocationRowTextView);
        locationTextView.setText(String.format("%s, %s", currentItem.getCity(), currentItem.getCountry()));

        return rowView;
    }

    @Override
    public int getCount() {
        if (mLocationsList != null)
        {
            return mLocationsList.size();
        }

        return 0;
    }
}

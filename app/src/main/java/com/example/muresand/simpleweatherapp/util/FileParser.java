package com.example.muresand.simpleweatherapp.util;

import android.content.Context;
import android.util.Log;

import com.example.muresand.simpleweatherapp.R;
import com.example.muresand.simpleweatherapp.server.CoordinatesDto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by muresand on 9/27/2017.
 */

public class FileParser {

    private FileParser() {
    }

    public static ArrayList<LocationModel> parseCityJsonArrayFromFile(Context context) {

        ArrayList<LocationModel> cities = new ArrayList<LocationModel>();

        try {

            InputStream inputStream = context.getResources().openRawResource(R.raw.city_list);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String inputStr;
            StringBuilder responseStrBuilder = new StringBuilder();
            while ((inputStr = reader.readLine()) != null)
            {
                responseStrBuilder.append(inputStr);
            }

            JSONArray array = new JSONArray(responseStrBuilder.toString());
            for (int i = 0; i < array.length(); i++) {
                LocationModel location =  new LocationModel();
                JSONObject currentJsonObject = array.getJSONObject(i);

                location.setCityId(currentJsonObject.getLong("id"));
                location.setCity(currentJsonObject.getString("name"));
                location.setCountry(currentJsonObject.getString("country"));

                JSONObject coordsSubObject = currentJsonObject.getJSONObject("coord");
                CoordinatesDto coords = new CoordinatesDto(coordsSubObject.getDouble("lat"), coordsSubObject.getDouble("lon"));
                location.setCoordinates(coords);

                cities.add(location);
            }

            return cities;
        }
        catch (Exception e)
        {
            Log.d("FILE_PARSER", e.getMessage());
            e.printStackTrace();

            return null;
        }
    }

}

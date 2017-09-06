package com.example.muresand.simpleweatherapp.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by muresand on 9/6/2017.
 */

public class WeatherDto {

    @SerializedName("id")
    private int mId;

    @SerializedName("main")
    private String mMain;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("icon")
    private String mIcon;

    public WeatherDto () {}

    public WeatherDto(int mId, String mMain, String mDescription, String mIcon) {
        this.mId = mId;
        this.mMain = mMain;
        this.mDescription = mDescription;
        this.mIcon = mIcon;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String mMain) {
        this.mMain = mMain;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }
}

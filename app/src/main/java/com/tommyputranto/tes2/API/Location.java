package com.tommyputranto.tes2.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 4/27/16.
 */
public class Location {
    @SerializedName("latitude")
    @Expose
    private List<String> latitude = new ArrayList<String>();
    @SerializedName("longitude")
    @Expose
    private List<String> longitude = new ArrayList<String>();

    /**
     *
     * @return
     * The latitude
     */
    public List<String> getLatitude2() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(List<String> latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public List<String> getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(List<String> longitude) {
        this.longitude = longitude;
    }

}

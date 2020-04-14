package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

public class LatLong {
    @SerializedName("latitude") public double lat;
    @SerializedName("longitude") public double longg;

    public LatLong(double lat, double longg) {
        this.lat = lat;
        this.longg = longg;
    }
}

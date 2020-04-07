package com.example.tripkuy.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatLongDTO {
    @SerializedName("latitude") public double lat;
    @SerializedName("longitude") public double longg;

    public LatLongDTO(double lat, double longg) {
        this.lat = lat;
        this.longg = longg;
    }
}

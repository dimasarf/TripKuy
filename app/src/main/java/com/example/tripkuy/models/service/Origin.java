package com.example.tripkuy.models.service;

import com.example.tripkuy.models.dto.LatLongDTO;
import com.google.gson.annotations.SerializedName;

public class Origin {
    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("address") public String address;
    @SerializedName("latLong") public LatLong latLong;
    @SerializedName("photoReference") public String photoReference;

    public Origin() {
    }

    public Origin(String name, LatLong latLong) {
        this.name = name;
        this.latLong = latLong;
    }
}

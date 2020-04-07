package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

public class Destination {
    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("address") public String address;
    @SerializedName("latLong") public LatLong latLong;
    @SerializedName("photoReference") public String photoReference;
}

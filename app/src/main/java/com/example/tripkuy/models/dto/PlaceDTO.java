package com.example.tripkuy.models.dto;

import com.google.gson.annotations.SerializedName;

public class PlaceDTO {
    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("address") public String address;
    @SerializedName("latLong") public LatLongDTO latLong;

    public PlaceDTO(String id, String name, String address, LatLongDTO latLong) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latLong = latLong;
    }
}

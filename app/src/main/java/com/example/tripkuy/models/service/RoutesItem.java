package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

public class RoutesItem {
    @SerializedName("origin") public Origin origin;
    @SerializedName("destination") public Destination destination;
    @SerializedName("tripDuration") public String tripDuration;
    @SerializedName("tripDistance") public String tripDistance;
    @SerializedName("startTime") public String startTime;
    @SerializedName("endTime") public String endTime;
    @SerializedName("lastTime") public String lastTime;
}


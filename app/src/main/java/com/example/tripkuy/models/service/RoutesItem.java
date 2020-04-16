package com.example.tripkuy.models.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoutesItem {
    @SerializedName("id") public String id;
    @SerializedName("origin") public Origin origin;
    @SerializedName("destination") public Destination destination;
    @SerializedName("tripDuration") public String tripDuration;
    @SerializedName("tripDistance") public String tripDistance;
    @SerializedName("startTime") public String startTime;
    @SerializedName("endTime") public String endTime;
    @SerializedName("lastTime") public String lastTime;
    @SerializedName("latitude") public double latitudeDB;
    @SerializedName("longitude") public double longitudeDB;
    @SerializedName("destinationDb") public String destinationDb;
    @SerializedName("rating") public float rating;
    @Expose
    @SerializedName("success") public Boolean success;
    @Expose
    @SerializedName("message") public String message;
}


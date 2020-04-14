package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItineraryDetailsItem {
    @SerializedName("id") public String id;
    @SerializedName("routes") public List<RoutesItem> routes;
    @SerializedName("day") public int day;
    @SerializedName("totalDistance") public String totalDistance;
    @SerializedName("totalDuration") public String totalDuration;
}

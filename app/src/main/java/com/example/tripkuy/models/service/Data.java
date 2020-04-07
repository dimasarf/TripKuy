package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("duration") public int duration;
    @SerializedName("endDate") public String endDate;
    @SerializedName("origin") public Origin origin;
    @SerializedName("name") public String name;
    @SerializedName("destinations") public List<Destination> destinations;
    @SerializedName("itineraryDetails") public List<ItineraryDetailsItem> itineraryDetails;
    @SerializedName("startDate") public String startDate;
    @SerializedName("totalDistance") public String totalDistance;
    @SerializedName("totalDuration") public String totalDuration;
}


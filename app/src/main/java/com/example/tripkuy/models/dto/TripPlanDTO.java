package com.example.tripkuy.models.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class TripPlanDTO {
    @SerializedName("startDate") public Date startDate;
    @SerializedName("endDate") public Date endDate;
    @SerializedName("duration") public long duration;
    @SerializedName("origin") public PlaceDTO origin;
    @SerializedName("name") public String name;
    @SerializedName("destinations") public ArrayList<PlaceDTO> destinations;

    public TripPlanDTO(Date startDate, Date endDate, long duration, PlaceDTO origin, String name, ArrayList<PlaceDTO> destinations) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.origin = origin;
        this.name = name;
        this.destinations = destinations;
    }
}

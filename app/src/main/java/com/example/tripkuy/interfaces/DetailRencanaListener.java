package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.RoutesItem;

import java.util.ArrayList;

public interface DetailRencanaListener {
    void getDetailRencana(ArrayList<ItineraryDetailsItem> items);
    void getSetRoutes(ArrayList<RoutesItem> items);
}

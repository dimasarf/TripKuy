package com.example.tripkuy.RecyclerItems;

import com.example.tripkuy.TripIteneraryActivity;
import com.example.tripkuy.tripssummary.TripSummaryFragment;

public class DetailTripItem {
    private int image;
    private String name, jarak, photoReference;

    public DetailTripItem(int image, String name, String jarak) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
    }

    public DetailTripItem(String name, String jarak, String photoReference) {
        this.name = name;
        this.jarak = jarak;
        this.photoReference = photoReference;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getJarak() {
        return jarak;
    }

    public String getPhotoReference() {
        return photoReference;
    }


}

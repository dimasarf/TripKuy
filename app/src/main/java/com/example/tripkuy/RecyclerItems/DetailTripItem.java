package com.example.tripkuy.RecyclerItems;

import com.example.tripkuy.TripIteneraryActivity;
import com.example.tripkuy.tripssummary.TripSummaryFragment;

public class DetailTripItem {
    private int image;
    private float rating;
    private String name, jarak, photoReference, durasi, id;

    public DetailTripItem(int image, String name, String jarak, String durasi) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
        this.durasi = durasi;
    }

    public DetailTripItem(int image, String name, String jarak, String durasi, String id, float rating) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
        this.durasi = durasi;
        this.id = id;
        this.rating = rating;
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

    public String getId() {
        return id;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public String getDurasi() {
        return durasi;
    }

    public float getRating() {
        return rating;
    }
}

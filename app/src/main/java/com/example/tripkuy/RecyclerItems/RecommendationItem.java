package com.example.tripkuy.RecyclerItems;

public class RecommendationItem {
    private int image;
    private String id, name, jarak, imageString;
    private String latitude;
    private String longitude;
    double similarity;
    public RecommendationItem(int image, String name, String jarak) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
    }

    public RecommendationItem(int image, String name, String jarak, String imageString) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
        this.imageString = imageString;
    }

    public RecommendationItem(String id, int resId, String nama, double similarity, String drawable, String latitude, String longitude) {
        this.id = id;
        this.image = resId;
        this.name = nama;
        this.similarity = similarity;
        this.imageString = drawable;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
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

    public double getSimilarity() {
        return similarity;
    }

    public String getImageString() {
        return imageString;
    }
}

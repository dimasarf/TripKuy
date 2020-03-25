package com.example.tripkuy.RecyclerItems;

public class RecommendationItem {
    private int image;
    private String name, jarak, imageString;

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

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getJarak() {
        return jarak;
    }

    public String getImageString() {
        return imageString;
    }
}

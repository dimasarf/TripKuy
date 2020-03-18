package com.example.tripkuy.RecyclerItems;

public class RecommendationItem {
    private int image;
    private String name, jarak;

    public RecommendationItem(int image, String name, String jarak) {
        this.image = image;
        this.name = name;
        this.jarak = jarak;
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
}

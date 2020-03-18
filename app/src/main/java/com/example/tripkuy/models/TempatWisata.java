package com.example.tripkuy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempatWisata {
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("drawable") private String drawable;

    public String getNama() {
        return nama;
    }

    public String getDrawable() {
        return drawable;
    }

    public TempatWisata(String nama) {
        this.nama = nama;
    }
}

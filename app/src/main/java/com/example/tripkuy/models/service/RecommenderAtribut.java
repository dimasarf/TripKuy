package com.example.tripkuy.models.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecommenderAtribut {
    @Expose
    @SerializedName("pengunjung") private String pengunjung;
    @Expose
    @SerializedName("activity") private String activity;
    @Expose
    @SerializedName("durasi") private long durasi;
    @Expose
    @SerializedName("email") private String email;

    public RecommenderAtribut(String pengunjung, String activity, long durasi, String email) {
        this.pengunjung = pengunjung;
        this.activity = activity;
        this.durasi = durasi;
        this.email = email;
    }

    public String getPengunjung() {
        return pengunjung;
    }

    public String getActivity() {
        return activity;
    }

    public long getDurasi() {
        return durasi;
    }

    public String getEmail() {
        return email;
    }
}

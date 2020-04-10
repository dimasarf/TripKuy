package com.example.tripkuy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengguna {
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("usia") private int usia;
    @Expose
    @SerializedName("email") private String email;
    @Expose
    @SerializedName("gender") private String gender;
    @Expose
    @SerializedName("preferensi") private String preferensi;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Pengguna() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferensi() {
        return preferensi;
    }

    public void setPreferensi(String preferensi) {
        this.preferensi = preferensi;
    }

    public Boolean getSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
}

package com.example.tripkuy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preferensi {
    @Expose
    @SerializedName("id_pengguna") private int id_pengguna;
    @Expose
    @SerializedName("tempat") private String tempat;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Preferensi(int id_pengguna, String tempat) {
        this.id_pengguna = id_pengguna;
        this.tempat = tempat;
    }

    public Preferensi(String tempat) {
        this.tempat = tempat;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}

package com.example.tripkuy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rencana {
    @Expose
    @SerializedName("id_pengguna") private int nama;
    @Expose
    @SerializedName("tempat") private String tempat;
    @Expose
    @SerializedName("tanggal_mulai") private String tanggal_mulai;
    @Expose
    @SerializedName("tanggal_akhir") private String tanggal_akhir;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Rencana() {
    }

    public int getNama() {
        return nama;
    }

    public void setNama(int nama) {
        this.nama = nama;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_akhir() {
        return tanggal_akhir;
    }

    public void setTanggal_akhir(String tanggal_akhir) {
        this.tanggal_akhir = tanggal_akhir;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

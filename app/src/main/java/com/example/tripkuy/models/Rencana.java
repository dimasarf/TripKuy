package com.example.tripkuy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rencana {
    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("id_pengguna") private int id_pengguna;
    @Expose
    @SerializedName("tempat") private String tempat;
    @Expose
    @SerializedName("durasi") private int durasi;
    @Expose
    @SerializedName("tanggal_mulai") private String tanggal_mulai;
    @Expose
    @SerializedName("tanggal_akhir") private String tanggal_akhir;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("kota") private String kota;
    @Expose
    @SerializedName("drawable") private String drawable;
    @Expose
    @SerializedName("origin") private String origin;
    @Expose
    @SerializedName("originLat") private double originLat;
    @Expose
    @SerializedName("originLong") private double originLong;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public Rencana() {
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getKota() {
        return kota;
    }

    public int getNama() {
        return id_pengguna;
    }

    public void setNama(int nama) {
        this.id_pengguna = nama;
    }

    public String getTempat() {
        return tempat;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getId() {
        return id;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setKota(String kota) {
        this.kota = kota;
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

    public String getOrigin() {
        return origin;
    }

    public double getOriginLat() {
        return originLat;
    }

    public double getOriginLong() {
        return originLong;
    }
}

package com.example.tripkuy.RecyclerItems;

public class TripsItem {
    private int image;
    private String kota, tanggal_mulai, tanggal_akhir;

    public TripsItem(int image, String kota, String tanggal_mulai) {
        this.image = image;
        this.kota = kota;
        this.tanggal_mulai = tanggal_mulai;
    }

    public TripsItem(int image, String kota, String tanggal_mulai, String tanggal_akhir) {
        this.image = image;
        this.kota = kota;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_akhir = tanggal_akhir;
    }

    public String getTanggal_akhir() {
        return tanggal_akhir;
    }

    public int getImage() {
        return image;
    }

    public String getKota() {
        return kota;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }
}

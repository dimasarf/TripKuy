package com.example.tripkuy.RecyclerItems;

public class TripsItem {
    private int image, durasi;
    private String kota, tanggal_mulai, tanggal_akhir, id;
    private String origin;
    private double originLat;
    private double originLong;

    public TripsItem(int image, String kota, String tanggal_mulai) {
        this.image = image;
        this.kota = kota;
        this.tanggal_mulai = tanggal_mulai;
    }

    public TripsItem(int image, String kota, String tanggal_mulai, String tanggal_akhir, String id, int durasi, String origin,
                     double originLat, double originLong) {
        this.image = image;
        this.kota = kota;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_akhir = tanggal_akhir;
        this.id = id;
        this.durasi = durasi;
        this.origin = origin;
        this.originLat= originLat;
        this.originLong = originLong;
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

    public int getDurasi() {
        return durasi;
    }

    public String getId() {
        return id;
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

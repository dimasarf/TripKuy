package com.example.tripkuy.models;

public class Pengguna {
    private String nama, email, jenis_kelamin, id_google;
    private int umur;

    public Pengguna() {
    }

    public String getId_google() {
        return id_google;
    }

    public void setId_google(String id_google) {
        this.id_google = id_google;
    }

    public Pengguna(String nama, String email, String jenis_kelamin, int umur) {
        this.nama = nama;
        this.email = email;
        this.jenis_kelamin = jenis_kelamin;
        this.umur = umur;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}

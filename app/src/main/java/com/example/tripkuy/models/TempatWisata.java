package com.example.tripkuy.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempatWisata implements Parcelable {
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("drawable") private String drawable;
    private int image;

    public TempatWisata(Parcel in) {
        nama = in.readString();
        drawable = in.readString();
    }

    public String getNama() {
        return nama;
    }

    public String getDrawable() {
        return drawable;
    }

    public TempatWisata(String nama) {
        this.nama = nama;
    }

    public TempatWisata(String nama, int image) {
        this.nama = nama;
        this.image = image;
    }

    public TempatWisata(String nama, String drawable) {
        this.nama = nama;
        this.drawable = drawable;
    }

    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(drawable);
    }

    public static final Parcelable.Creator<TempatWisata> CREATOR = new Parcelable.Creator<TempatWisata>() {
        public TempatWisata createFromParcel(Parcel in) {
            return new TempatWisata(in);
        }

        public TempatWisata[] newArray(int size) {
            return new TempatWisata[size];
        }
    };
}

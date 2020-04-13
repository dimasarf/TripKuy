package com.example.tripkuy.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempatWisata implements Parcelable {
    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("name") private String nama;
    @Expose
    @SerializedName("drawable") private String drawable;
    @Expose
    @SerializedName("latitude") private String latitude;
    @Expose
    @SerializedName("longitude") private String longitude;
    @Expose
    @SerializedName("simQueryLocation") private double similarity;
    private int image;

    public TempatWisata(Parcel in) {
        nama = in.readString();
        drawable = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public String getNama() {
        return nama;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
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

    public TempatWisata(String id, String nama, String drawable, String latitude, String longitude) {
        this.id = id;
        this.nama = nama;
        this.drawable = drawable;
        this.latitude = latitude;
        this.longitude = longitude;
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
        parcel.writeString(latitude);
        parcel.writeString(longitude);
    }

    public String getId() {
        return id;
    }

    public static final Parcelable.Creator<TempatWisata> CREATOR = new Parcelable.Creator<TempatWisata>() {
        public TempatWisata createFromParcel(Parcel in) {
            return new TempatWisata(in);
        }

        public TempatWisata[] newArray(int size) {
            return new TempatWisata[size];
        }
    };

    public double getSimilarity() {
        return similarity;
    }
}

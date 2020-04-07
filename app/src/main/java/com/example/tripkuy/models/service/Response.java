package com.example.tripkuy.models.service;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("data") public Data data;
    @SerializedName("runtime") public int runtime;
    @SerializedName("status") public String status ;

    public Data getData() {
        return data;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getStatus() {
        return status;
    }
}

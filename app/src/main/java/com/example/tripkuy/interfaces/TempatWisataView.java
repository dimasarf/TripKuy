package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.TempatWisata;

import java.util.List;

public interface TempatWisataView {
    void onGetResult(List<TempatWisata> tempatWisatas);
    void onErrorLoading(String message);
}

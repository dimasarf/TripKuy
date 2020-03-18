package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.TempatWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiTempatWisataInterface {
    @GET("tempat_wisatas.php")
    Call<List<TempatWisata>> getAll();
}

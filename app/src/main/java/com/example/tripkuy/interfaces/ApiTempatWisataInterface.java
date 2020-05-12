package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.TempatWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiTempatWisataInterface {
    @FormUrlEncoded
    @POST("get_rekomendasi.php")
    Call<List<TempatWisata>> getAll(
            @Field("pengunjung") String pengunjung,
            @Field("activity") String activity,
            @Field("durasi") long durasi,
            @Field("email") String email
    );

    @GET("locations.php")
    Call<List<TempatWisata>> get_locations();

}

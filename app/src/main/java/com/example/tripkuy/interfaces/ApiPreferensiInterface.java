package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.Preferensi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiPreferensiInterface {
    @FormUrlEncoded
    @POST("save_preferensi.php")
    Call<Preferensi> savePreferensi(
            @Field("id_pengguna") int id_pengguna,
            @Field("tempat") String tempat
    );
}

package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Preferensi;
import com.example.tripkuy.models.Rencana;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRencanaInterface {
    @FormUrlEncoded
    @POST("save_rencana.php")
    Call<Rencana> saveRencana(
            @Field("email") String email,
            @Field("tempat") String tempat,
            @Field("tanggal_mulai") String tanggal_mulai,
            @Field("tanggal_akhir") String tanggal_akhir
    );
}

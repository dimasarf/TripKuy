package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Preferensi;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.TempatWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRencanaInterface {
    @FormUrlEncoded
    @POST("save_rencana.php")
    Call<Rencana> saveRencana(
            @Field("email") String email,
            @Field("tempat") String tempat,
            @Field("tanggal_mulai") String tanggal_mulai,
            @Field("tanggal_akhir") String tanggal_akhir,
            @Field("durasi") int durasi
    );

    @FormUrlEncoded
    @POST("save_detailrencana.php")
    Call<Rencana> saveDetailRencana(
            @Field("idrencana") String idrencana,
            @Field("durasi") String durasi,
            @Field("jarak") String jarak,
            @Field("day") int day
    );

    @FormUrlEncoded
    @POST("save_rute.php")
    Call<Rencana> saveRute(
            @Field("iddetailrencana") String id_rencana,
            @Field("tempat") String tempat,
            @Field("jarak") String jarak,
            @Field("durasi") String durasi
    );

    @GET("rencanas.php")
    Call<List<Rencana>> getAll(
            @Query("email") String email
    );
}

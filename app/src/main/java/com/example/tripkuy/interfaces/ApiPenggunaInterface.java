package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.service.RoutesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiPenggunaInterface {

    @FormUrlEncoded
    @POST("save_pengguna.php")
    Call<Pengguna> savePengguna(
            @Field("nama") String nama,
            @Field("usia") int usia,
            @Field("email") String email,
            @Field("gender") String gender,
            @Field("preferensi") String preferensi
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<Pengguna> updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Pengguna> deleteNote( @Field("id") int id );

    @GET("kategori.php")
    Call<String> getKategori(
            @Query("email") String email
    );

    @FormUrlEncoded
    @POST("update_kategori.php")
    Call<Pengguna> updateKategori(
            @Field("email") String email,
            @Field("kategori") String kategori
    );
}

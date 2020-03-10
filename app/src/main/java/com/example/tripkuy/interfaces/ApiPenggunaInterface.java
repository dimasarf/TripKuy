package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.Pengguna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPenggunaInterface {

    @FormUrlEncoded
    @POST("save_pengguna.php")
    Call<Pengguna> saveNote(
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @GET("notes.php")
    Call<List<Pengguna>> getNotes();

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
}
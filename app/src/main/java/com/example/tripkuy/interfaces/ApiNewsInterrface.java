package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.service.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNewsInterrface {
    @GET("everything")
    Call<NewsResponse> getLatestNews(@Query("q") String key, @Query("apiKey") String apiKey);
}

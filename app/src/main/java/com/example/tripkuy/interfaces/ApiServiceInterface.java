package com.example.tripkuy.interfaces;

import com.example.tripkuy.models.dto.TripPlanDTO;
import com.example.tripkuy.models.service.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceInterface {
    @POST("itineraries")
    Call<Response> process(
            @Body TripPlanDTO tripPlanDTO
    );

    @GET("/")
    Call<Response> test();

    void dataListener(Response response);
}

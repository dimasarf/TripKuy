package com.example.tripkuy.Handler;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiTempatWisataInterface;
import com.example.tripkuy.interfaces.TempatWisataView;
import com.example.tripkuy.models.TempatWisata;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempatWisataHandler {
    private TempatWisataView tempatWisataView;

    public TempatWisataHandler(TempatWisataView tempatWisataView) {
        this.tempatWisataView = tempatWisataView;
    }

    public void getData(){
        ApiTempatWisataInterface apiTempatWisataInterface = ApiClient.getApiClient().create(ApiTempatWisataInterface.class);
        Call<List<TempatWisata>> call = apiTempatWisataInterface.getAll();
        call.enqueue(new Callback<List<TempatWisata>>() {
            @Override
            public void onResponse(Call<List<TempatWisata>> call, Response<List<TempatWisata>> response) {
                if(response.isSuccessful()){
                    tempatWisataView.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TempatWisata>> call, Throwable t) {

            }
        });
    }
}

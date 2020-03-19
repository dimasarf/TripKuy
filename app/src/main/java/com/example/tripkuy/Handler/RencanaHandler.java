package com.example.tripkuy.Handler;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.interfaces.RencanaView;
import com.example.tripkuy.models.Rencana;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RencanaHandler {
    private RencanaView rencanaView;
    private String email;

    public RencanaHandler(RencanaView rencanaView, String email) {
        this.rencanaView = rencanaView;
        this.email = email;
    }

    public RencanaHandler(RencanaView rencanaView) {
        this.rencanaView = rencanaView;
    }

    public void getData(){
        ApiRencanaInterface apiRencanaInterface = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        Call<List<Rencana>> call = apiRencanaInterface.getAll(this.email);
        call.enqueue(new Callback<List<Rencana>>() {
            @Override
            public void onResponse(Call<List<Rencana>> call, Response<List<Rencana>> response) {
                if(response.isSuccessful()){
                    rencanaView.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Rencana>> call, Throwable t) {

            }
        });
    }
}

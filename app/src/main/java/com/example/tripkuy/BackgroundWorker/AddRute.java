package com.example.tripkuy.BackgroundWorker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.service.Data;
import com.example.tripkuy.models.service.RoutesItem;

import java.util.ArrayList;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRute extends AsyncTask<Void, Void, Void> {

    ArrayList<RoutesItem> data;
    String idDetailRencana;
    Context context;
    ApiRencanaInterface apiRencanaInterface;

    public AddRute(ArrayList<RoutesItem> data, String idDetailRencana, Context context) {
        this.data = data;
        this.idDetailRencana = idDetailRencana;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        apiRencanaInterface = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        for (RoutesItem item: data) {
            Call<Rencana> call = apiRencanaInterface.saveRute(idDetailRencana, item.destination.name,
                    item.tripDistance, item.tripDuration, item.destination.latLong.lat, item.destination.latLong.longg);
            call.enqueue(new Callback<Rencana>() {
                @Override
                public void onResponse(Call<Rencana> call, Response<Rencana> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Boolean success = response.body().getSuccess();
                        if(success){
                            Log.d("Berhasil!!", "pesan "+response.body().getMessage() );
                        }
                        else {
                            Toast.makeText(context,response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Log.d("errorrencana", "error "+response.body().getMessage() );
                        }
                    }
                }

                @Override
                public void onFailure(Call<Rencana> call, Throwable t) {
                    Toast.makeText(context, t.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            });
        }
        return null;
    }
}

package com.example.tripkuy.BackgroundWorker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.service.Data;
import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.RoutesItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.startActivity;

public class AddDetailRencana extends AsyncTask<Void, Void, Void> {

    Data data;
    String idRencana;
    Context context;
    ApiRencanaInterface apiRencanaInterface;
    Intent intent;

    public AddDetailRencana(Data data, String idRencana, Context context, Intent intent) {
        this.data = data;
        this.idRencana = idRencana;
        this.context = context;
        this.intent = intent;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Void s) {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        apiRencanaInterface = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        for (final ItineraryDetailsItem item : data.itineraryDetails) {
            Call<Rencana> call = apiRencanaInterface.saveDetailRencana(idRencana, item.totalDuration, item.totalDistance, item.day);
            call.enqueue(new Callback<Rencana>() {
                @Override
                public void onResponse(Call<Rencana> call, Response<Rencana> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Boolean success = response.body().getSuccess();
                        if(success){
                            String inserted_id = response.body().getMessage();
                            AddRute addRute = new AddRute((ArrayList<RoutesItem>) item.routes, inserted_id, context);
                            addRute.execute();
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
        context.startActivity(intent);
        return  null;
    }
}

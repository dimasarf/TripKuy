package com.example.tripkuy.BackgroundWorker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tripkuy.Dashboard;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.RoutesItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRating extends AsyncTask<Void, Void, Void> {
    private String id;
    private float rating;
    private Context context;
    private ProgressDialog dialog;

    public UpdateRating(String id, float rating, Context context) {
        this.id = id;
        this.rating = rating;
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Menyimpan Rating");
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiRencanaInterface apiDetailRencana = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        Call<RoutesItem> call = apiDetailRencana.updateRating(id, rating);
        call.enqueue(new Callback<RoutesItem>() {
            @Override
            public void onResponse(Call<RoutesItem> call, Response<RoutesItem> response) {
                if(response.isSuccessful() && response.body() != null){
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }else {
                    Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<RoutesItem> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        return null;
    }
}

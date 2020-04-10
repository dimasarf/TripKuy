package com.example.tripkuy.BackgroundWorker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tripkuy.Dashboard;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.interfaces.ApiPreferensiInterface;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.Preferensi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPreferensi extends AsyncTask<Void, Void, Void> {
    Context context;
    ApiPreferensiInterface apiPreferensi;
    List<Preferensi> preferensiList;
    String id_pengguna;

    public AddPreferensi(Context ctx,List<Preferensi> _preferensiList, String _id_pengguna){
        context = ctx;
        preferensiList = _preferensiList;
        id_pengguna = _id_pengguna;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Void s) {
    }

    @Override
    protected Void doInBackground(Void... params) {
        apiPreferensi = ApiClient.getApiClient().create(ApiPreferensiInterface.class);
        for (Preferensi preferensi: preferensiList) {
            Call<Preferensi> call = apiPreferensi.savePreferensi(Integer.parseInt(id_pengguna), preferensi.getTempat());

            call.enqueue(new Callback<Preferensi>() {
                @Override
                public void onResponse(Call<Preferensi> call, Response<Preferensi> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Boolean success = response.body().getSuccess();
                        if(success){
                            String inserted_id = response.body().getMessage();
                            Toast.makeText(context,
                                    "Pendaftaran Berhasil!",Toast.LENGTH_SHORT).show();
                            ;
                        }
                        else {
                            Toast.makeText(context,response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Preferensi> call, Throwable t) {
                    Toast.makeText(context, t.getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            });
        }

        return null;
    }
}

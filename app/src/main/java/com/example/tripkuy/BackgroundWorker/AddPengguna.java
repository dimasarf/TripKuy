package com.example.tripkuy.BackgroundWorker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tripkuy.Dashboard;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.Preferensi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPengguna extends AsyncTask<Void, Void, Void> {
    ProgressDialog progressDialog;
    Context context;
    ApiPenggunaInterface apiPenggunaInterface;
    Pengguna pengguna;
    public AddPengguna(Context ctx, Pengguna _pengguna){
        context = ctx;
        pengguna = _pengguna;
        progressDialog = new ProgressDialog(ctx);
    }

    public AddPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(Void s) {

    }

    @Override
    protected Void doInBackground(Void... params) {
        apiPenggunaInterface = ApiClient.getApiClient().create(ApiPenggunaInterface.class);
        Call<Pengguna> call = apiPenggunaInterface.savePengguna(pengguna.getNama(), pengguna.getUsia(), pengguna.getEmail(), pengguna.getGender(), pengguna.getPreferensi());

        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                progressDialog.dismiss();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();

                    if(success){
                        Toast.makeText(context,
                                "Pendaftaran Berhasil!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Dashboard.class);
                        context.startActivity(intent);
                    }
                    else {
                        Toast.makeText(context,response.body().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Pengguna> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }
}

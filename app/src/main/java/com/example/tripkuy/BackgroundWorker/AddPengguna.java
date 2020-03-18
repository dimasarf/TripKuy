package com.example.tripkuy.BackgroundWorker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
    List<Preferensi> listPreferensi;

    public AddPengguna(Context ctx, Pengguna _pengguna, List<Preferensi> preferensiList){
        context = ctx;
        pengguna = _pengguna;
        progressDialog = new ProgressDialog(ctx);
        listPreferensi = preferensiList;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void s) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        apiPenggunaInterface = ApiClient.getApiClient().create(ApiPenggunaInterface.class);
        Call<Pengguna> call = apiPenggunaInterface.savePengguna(pengguna.getNama(), pengguna.getUsia(), pengguna.getEmail(), pengguna.getGender());

        call.enqueue(new Callback<Pengguna>() {
            @Override
            public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                progressDialog.dismiss();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        String inserted_id = response.body().getMessage();
                        AddPreferensi addPreferensi = new AddPreferensi(context, listPreferensi, inserted_id);
                        addPreferensi.execute();
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

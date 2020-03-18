package com.example.tripkuy.BackgroundWorker;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPreferensiInterface;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.models.Preferensi;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.TempatWisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRencana extends AsyncTask<Void, Void, Void> {
    Context context;
    ApiRencanaInterface apiRencanaInterface;
    List<TempatWisata> tempatWisatas;
    String email, tanggal_awal, tanggal_akhir;

    public AddRencana(Context context, ArrayList<TempatWisata> tempatWisatas, String email, String tanggal_awal, String tanggal_akhir) {
        this.context = context;
        this.tempatWisatas = tempatWisatas;
        this.email = email;
        this.tanggal_awal = tanggal_awal;
        this.tanggal_akhir = tanggal_akhir;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Void s) {
    }

    @Override
    protected Void doInBackground(Void... params) {
        apiRencanaInterface = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        for (TempatWisata item: tempatWisatas) {
            Call<Rencana> call = apiRencanaInterface.saveRencana(email, item.getNama(), tanggal_awal, tanggal_akhir);

            call.enqueue(new Callback<Rencana>() {
                @Override
                public void onResponse(Call<Rencana> call, Response<Rencana> response) {
                    if(response.isSuccessful() && response.body() != null){
                        Boolean success = response.body().getSuccess();
                        if(success){
                            String inserted_id = response.body().getMessage();
                            Toast.makeText(context,
                                    "Pendaftaran Berhasil!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
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

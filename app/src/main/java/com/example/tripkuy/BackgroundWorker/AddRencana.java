package com.example.tripkuy.BackgroundWorker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPreferensiInterface;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.models.Preferensi;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.service.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.startActivity;

public class AddRencana extends AsyncTask<Void, Void, Void> {
    Context context;
    ApiRencanaInterface apiRencanaInterface;
    List<TempatWisata> tempatWisatas;
    String email, tanggal_awal, tanggal_akhir;
    Intent intent;
    Data data;
    String penginapan;
    double penginapanLat, penginapanLong;

    public AddRencana(Context context, ArrayList<TempatWisata> tempatWisatas, String email, String tanggal_awal) {
        this.context = context;
        this.tempatWisatas = tempatWisatas;
        this.email = email;
        this.tanggal_awal = tanggal_awal;
        this.tanggal_akhir = tanggal_akhir;
    }

    public AddRencana(Context context, Data data, String email, Intent intent, String penginapan, Double penginapanLat, Double penginapanLong) {
        this.context = context;
        this.data = data;
        this.email = email;
        this.intent = intent;
        this.penginapan = penginapan;
        this.penginapanLat = penginapanLat;
        this.penginapanLong = penginapanLong;
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
        Call<Rencana> call = apiRencanaInterface.saveRencana(email, "Yogyakarta", data.startDate, data.endDate, data.duration, penginapan, penginapanLat, penginapanLong);

        call.enqueue(new Callback<Rencana>() {
            @Override
            public void onResponse(Call<Rencana> call, Response<Rencana> response) {
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        String inserted_id = response.body().getMessage();
                        Toast.makeText(context,
                                "Pembuatan Rencana Berhasil!",Toast.LENGTH_SHORT).show();
                        AddDetailRencana detailRencana = new AddDetailRencana(data, inserted_id, context, intent);
                        Log.d("idrencana", "id "+inserted_id);
                        detailRencana.execute();

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

        return null;
    }
}

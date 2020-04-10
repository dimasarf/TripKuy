package com.example.tripkuy.Handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiTempatWisataInterface;
import com.example.tripkuy.interfaces.TempatWisataView;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.service.RecommenderAtribut;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempatWisataHandler {
    private TempatWisataView tempatWisataView;
    RecommenderAtribut atribut;
    private ProgressDialog dialog;
    private Context context;

    public TempatWisataHandler(TempatWisataView tempatWisataView) {
        this.tempatWisataView = tempatWisataView;
    }

    public TempatWisataHandler(TempatWisataView tempatWisataView, RecommenderAtribut recommenderAtribut, Context context) {
        this.tempatWisataView = tempatWisataView;
        this.atribut = recommenderAtribut;
        dialog = new ProgressDialog(context);
        this.context = context;
        dialog.setMessage("Tunggu sebentar");
    }

    public void getData(){
        ApiTempatWisataInterface apiTempatWisataInterface = ApiClient.getApiClient().create(ApiTempatWisataInterface.class);
        Call<List<TempatWisata>> call = apiTempatWisataInterface.getAll(atribut.getPengunjung(), atribut.getActivity(), atribut.getDurasi(), atribut.getEmail());
        Log.d("pengunjung", "pengunjung " +atribut.getPengunjung());
        Log.d("kegiatan", "kegiatan " +atribut.getActivity());
        dialog.show();
        call.enqueue(new Callback<List<TempatWisata>>() {
            @Override
            public void onResponse(Call<List<TempatWisata>> call, Response<List<TempatWisata>> response) {
                if(response.isSuccessful()){
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    else {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT);
                        }
                    }
                    tempatWisataView.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TempatWisata>> call, Throwable t) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT);
                }
            }
        });
    }
}

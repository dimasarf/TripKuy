package com.example.tripkuy.BackgroundWorker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tripkuy.Dashboard;
import com.example.tripkuy.TripIteneraryActivity;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiRencanaInterface;
import com.example.tripkuy.interfaces.DetailRencanaListener;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.service.Data;
import com.example.tripkuy.models.service.Destination;
import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.LatLong;
import com.example.tripkuy.models.service.Origin;
import com.example.tripkuy.models.service.RoutesItem;
import com.example.tripkuy.tripssummary.TripSummaryActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDetailRencana extends AsyncTask<Void, Void, Void> implements DetailRencanaListener {

    private String id_rencana;
    private int durasi;
    private String tgl_awal, tgl_akhir;
    private Context context;
    private Origin origin;
    Data data = new Data();
    int i = 0;
    private DetailRencanaListener detailRencanaListener;
    ArrayList<ItineraryDetailsItem> itineraryDetailsItems;
    public GetDetailRencana(String id_rencana, Context context, int durasi, String tgl_awal, String tgl_akhir, Origin origin) {
        this.id_rencana = id_rencana;
        this.durasi = durasi;
        this.tgl_awal = tgl_awal;
        this.tgl_akhir = tgl_akhir;
        this.context = context;
        this.origin = origin;
        detailRencanaListener = this;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiRencanaInterface apiDetailRencana = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        Call<List<ItineraryDetailsItem>> call = apiDetailRencana.getDetailRencana(id_rencana);
        call.enqueue(new Callback<List<ItineraryDetailsItem>>() {
            @Override
            public void onResponse(Call<List<ItineraryDetailsItem>> call, Response<List<ItineraryDetailsItem>> response) {
                detailRencanaListener.getDetailRencana((ArrayList<ItineraryDetailsItem>) response.body());
            }

            @Override
            public void onFailure(Call<List<ItineraryDetailsItem>> call, Throwable t) {

            }
        });
        return null;
    }

    @Override
    public void getDetailRencana(ArrayList<ItineraryDetailsItem> items) {
        ApiRencanaInterface apiDetailRencana = ApiClient.getApiClient().create(ApiRencanaInterface.class);
        itineraryDetailsItems = null;
        itineraryDetailsItems = items;
        for (int j = 0; j < itineraryDetailsItems.size(); j++) {
            Call<List<RoutesItem>> call = apiDetailRencana.getRoutes(itineraryDetailsItems.get(j).id);
            final int finalJ = j;
            call.enqueue(new Callback<List<RoutesItem>>() {
                @Override
                public void onResponse(Call<List<RoutesItem>> call, Response<List<RoutesItem>> response) {
                    ArrayList<RoutesItem> itemss = (ArrayList<RoutesItem>) response.body();

                    for(int l =0; l < itemss.size(); l++ ){
                        itemss.get(l).destination = new Destination(itemss.get(l).destinationDb,
                                new LatLong(itemss.get(l).latitudeDB, itemss.get(l).longitudeDB));
                    }
                    itineraryDetailsItems.get(finalJ).routes = itemss;
                    detailRencanaListener.getSetRoutes((ArrayList<RoutesItem>) response.body());
                }

                @Override
                public void onFailure(Call<List<RoutesItem>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void getSetRoutes(ArrayList<RoutesItem> items) {
        i++;

        if(i > itineraryDetailsItems.size() - 1){

            Data data = new Data();
            data.origin = origin;
            data.startDate = tgl_awal;
            data.endDate = tgl_akhir;
            data.itineraryDetails = itineraryDetailsItems;
            data.duration = durasi;
            com.example.tripkuy.models.service.Response response = new com.example.tripkuy.models.service.Response();
            response.data = data;
            TripSummaryActivity.response = null;
            TripSummaryActivity.response = response;
            Intent intent = new Intent(context, TripSummaryActivity.class);
            context.startActivity(intent);
        }
    }
}

package com.example.tripkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tripkuy.BackgroundWorker.AddRencana;
import com.example.tripkuy.RecyclerAdapter.DetailTripsAdapter;
import com.example.tripkuy.RecyclerAdapter.RecommendationAdapter;
import com.example.tripkuy.RecyclerItems.DetailTripItem;
import com.example.tripkuy.interfaces.OnTaskReadyCallback;
import com.example.tripkuy.models.TempatWisata;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DetailTripActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskReadyCallback {
    String tgl_awal, tgl_akhir, email;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    private MapView mapView;
    private GoogleMap gmap;
    private RecyclerView mRecyclerView;
    private DetailTripsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private ArrayList<DetailTripItem> detailTripItems = new ArrayList<>();
    private TextView mTanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);
        mTanggal = findViewById(R.id.tanggal);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync((OnMapReadyCallback) this);

        Intent intent = getIntent();
        tgl_awal = intent.getStringExtra(RecommendationActivity.TGLAWAL);
        tgl_akhir = intent.getStringExtra(RecommendationActivity.TGLAKHIR);
        email = intent.getStringExtra(RecommendationActivity.EMAIL);
        selectedTempatWisatas = intent.getParcelableArrayListExtra(RecommendationActivity.SELECTEDTEMPATWISATA);
        mTanggal.setText(tgl_awal +" - "+tgl_akhir);
        buildRecyclerView();

    }

    public void simpanPerjalanan(View view){
        AddRencana addRencana = new AddRencana(this, selectedTempatWisatas,email, tgl_awal, tgl_akhir);
        addRencana.execute();
    }

    private void buildRecyclerView() {
        for (TempatWisata item: selectedTempatWisatas) {
            int resId = getResId(item.getDrawable(), R.drawable.class);
            detailTripItems.add(new DetailTripItem(resId, item.getNama(), "5 K.M"));
        }
        mRecyclerView = findViewById(R.id.recycler_detail_trips);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DetailTripsAdapter(detailTripItems);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng ny = new LatLng(40.7143528, -74.0059731);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
    }

    @Override
    public void onTaskDone(Object... values) {

    }
}

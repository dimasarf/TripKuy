package com.example.tripkuy;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tripkuy.RecyclerAdapter.DetailTripsAdapter;
import com.example.tripkuy.RecyclerItems.DetailTripItem;
import com.example.tripkuy.models.TempatWisata;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TripIteneraryActivity extends FragmentActivity implements OnMapReadyCallback {
    String tgl_awal, tgl_akhir, email;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    GoogleMap map;
    MarkerOptions place1, place2;
    ConstraintLayout layout;
    private RecyclerView mRecyclerView;
    private DetailTripsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DetailTripItem> detailTripItems = new ArrayList<>();
    BottomSheetBehavior bottomSheetBehavior;
    private TextView mTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_itenerary);
        mTanggal = findViewById(R.id.tanggal);
        layout = (ConstraintLayout) findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        place1 = new MarkerOptions().position(new LatLng(-7.901335, 110.437509));
        place2 = new MarkerOptions().position(new LatLng(-7.926269, 110.435514));
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        tgl_awal = intent.getStringExtra(RecommendationActivity.TGLAWAL);
        tgl_akhir = intent.getStringExtra(RecommendationActivity.TGLAKHIR);
        email = intent.getStringExtra(RecommendationActivity.EMAIL);
        selectedTempatWisatas = intent.getParcelableArrayListExtra(RecommendationActivity.SELECTEDTEMPATWISATA);
        mTanggal.setText(tgl_awal +" - "+tgl_akhir);
        buildRecyclerView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(place1);
        map.addMarker(place2);
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
}

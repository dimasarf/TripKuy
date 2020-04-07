package com.example.tripkuy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.RecyclerAdapter.DetailTripsAdapter;
import com.example.tripkuy.RecyclerItems.DetailTripItem;
import com.example.tripkuy.interfaces.ApiServiceInterface;
import com.example.tripkuy.interfaces.OnTaskReadyCallback;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.dto.TripPlanDTO;
import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.RoutesItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class TripIteneraryActivity extends FragmentActivity implements OnMapReadyCallback, OnTaskReadyCallback, ApiServiceInterface {
    String tgl_awal, tgl_akhir, email, penginapan;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    GoogleMap map;
    double penginapanLat, penginapanLong;
    ConstraintLayout layout;
    private RecyclerView mRecyclerView;
    private DetailTripsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DetailTripItem> detailTripItems = new ArrayList<>();
    BottomSheetBehavior bottomSheetBehavior;
    private TextView mTanggal;
    private RequestQueue mQueue;
    List<LatLng> latLngList = new ArrayList<>();
    public  static com.example.tripkuy.models.service.Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_itenerary);
        mTanggal = findViewById(R.id.tanggal);
        layout = (ConstraintLayout) findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        tgl_awal = intent.getStringExtra(RecommendationActivity.TGLAWAL);
        tgl_akhir = intent.getStringExtra(RecommendationActivity.TGLAKHIR);
        email = intent.getStringExtra(RecommendationActivity.EMAIL);
        penginapan = intent.getStringExtra(RecommendationActivity.ORIGIN);
        selectedTempatWisatas = intent.getParcelableArrayListExtra(RecommendationActivity.SELECTEDTEMPATWISATA);
        penginapanLat = intent.getDoubleExtra(NewTripActivity.ORIGINLAT, 0.0);
        penginapanLong = intent.getDoubleExtra(NewTripActivity.ORIGINLONG, 0.0);
        mTanggal.setText(tgl_awal +" - "+tgl_akhir);
        buildRecyclerView();
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<ItineraryDetailsItem> itineraryDetailsItems = (ArrayList<ItineraryDetailsItem>) response.data.itineraryDetails;

        for(int i = 0; i < itineraryDetailsItems.size(); i++){
            for (RoutesItem item: itineraryDetailsItems.get(i).routes) {
                Log.d("PLAN", "DAY "+itineraryDetailsItems.get(i).day + " DESTINATION " + item.destination.name);
            }
        }
        Log.d("itinerary detail",response.data.destinations.get(0).name);
        map = googleMap;
        map.addMarker(new
                MarkerOptions().position(
                        new LatLng(penginapanLat, penginapanLong))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );
        for (TempatWisata tempatWisata: selectedTempatWisatas) {
            Log.d("KOORDINAT", tempatWisata.getLatitude() + " - "+ tempatWisata.getLongitude());
            map.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(tempatWisata.getLatitude()),Double.valueOf(tempatWisata.getLongitude()))));
        }
        jsonParse();
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

    private void jsonParse() {
        String destination = " ", origin =" ", waypoints= " ";
        for (int i = 0; i< selectedTempatWisatas.size(); i++){
            origin = penginapan+"&";
            destination = selectedTempatWisatas.get(selectedTempatWisatas.size()-1).getNama()+"&";
            if(i != (selectedTempatWisatas.size() -1)){
                if(waypoints == " "){
                    waypoints += selectedTempatWisatas.get(i).getNama()+",";
                }
                else if(i == (selectedTempatWisatas.size()-2)){
                    waypoints += selectedTempatWisatas.get(i).getNama()+"&";
                }
                else {
                    waypoints += ","+selectedTempatWisatas.get(i).getNama();
                }
            }

        }
        String url = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + origin + "destination=" + destination + "waypoints=" + waypoints +
                "key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        Log.d("origin", origin);
        Log.d("waypoints", waypoints);
        Log.d("destination", destination);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("routes");
                    JSONObject singleRout = (JSONObject) jsonArray.get(0);
                    JSONObject overview_polyline = (JSONObject) singleRout.get("overview_polyline");
                    if (overview_polyline != null) {
                        String points = overview_polyline.getString("points");
                        latLngList = PolyUtil.decode(points);


                        Log.d("KONTOLLL", latLngList.get(0).longitude+"tolol");
                        for (LatLng latLng : latLngList){
                            Log.d("POLY", latLng.latitude + " " +latLng.longitude);
                        }
                        map.addPolyline(new PolylineOptions()
                                .color(getResources().getColor(R.color.colorAccent)) // Line color.
                                .width(10) // Line width.
                                .clickable(false) // Able to click or not.
                                .addAll(latLngList));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });
        mQueue.add(request);

    }

    @Override
    public void onTaskDone(Object... values) {
        map.addPolyline(new PolylineOptions()
                .color(getResources().getColor(R.color.colorAccent)) // Line color.
                .width(10) // Line width.
                .clickable(false) // Able to click or not.
                .addAll(latLngList));
    }

    @Override
    public Call<com.example.tripkuy.models.service.Response> process(TripPlanDTO tripPlanDTO) {
        return null;
    }

    @Override
    public Call<com.example.tripkuy.models.service.Response> test() {
        return null;
    }

    @Override
    public void dataListener(com.example.tripkuy.models.service.Response response) {
//        this.response = response;
    }
}

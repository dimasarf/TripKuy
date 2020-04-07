package com.example.tripkuy.tripssummary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerAdapter.DetailTripsAdapter;
import com.example.tripkuy.RecyclerItems.DetailTripItem;
import com.example.tripkuy.home.HomeViewModel;
import com.example.tripkuy.interfaces.GoogleMapAPICallback;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.service.ItineraryDetailsItem;
import com.example.tripkuy.models.service.RoutesItem;
import com.example.tripkuy.registration.GenderFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TripSummaryFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private Button btn_trip_baru;
    public static com.example.tripkuy.models.service.Response response;
    String tgl_awal, tgl_akhir, email, penginapan;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    GoogleMap map;
    MapView mapView;
    int day;
    double penginapanLat, penginapanLong;
    ConstraintLayout layout;
    private RecyclerView mRecyclerView;
    private DetailTripsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DetailTripItem> detailTripItems = new ArrayList<>();
    BottomSheetBehavior bottomSheetBehavior;
    private TextView mDurasi, mHari, mJarak;
    private static RequestQueue mQueue;
    List<LatLng> latLngList = new ArrayList<>();
    View root;

    public static TripSummaryFragment newInstance(int day) {
        TripSummaryFragment fragment = new TripSummaryFragment();
        Bundle args = new Bundle();
        args.putInt("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_trip_summary, container, false);
        day = getArguments().getInt("day", 0);
        mDurasi = root.findViewById(R.id.waktu);
        mHari = root.findViewById(R.id.hari);
        mJarak = root.findViewById(R.id.jarak);
        layout = (ConstraintLayout) root.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapFrag2);
        mapFragment.getMapAsync(this);
        mQueue = Volley.newRequestQueue(getContext());
        buildRecyclerView();

        return root;

    }

    private void buildRecyclerView() {
        ItineraryDetailsItem itineraryDetail = response.data.itineraryDetails.get(day);
        mDurasi.setText(itineraryDetail.totalDuration);
        mHari.setText("Hari "+itineraryDetail.day);
        mJarak.setText(itineraryDetail.totalDistance);

        for (final RoutesItem item: itineraryDetail.routes) {

            getPhotoReference("tugu jogja", new GoogleMapAPICallback() {
                @Override
                public void onPhotoReferenceCallback(String photoReference) {
                    detailTripItems.add(new DetailTripItem(item.destination.name, item.tripDistance, item.destination.photoReference));
                    Log.d("photoRef", photoReference);
                }
            });


            Log.d("PhotoTEST", "FOTO TEST"+detailTripItems.get(0).getPhotoReference());
            Log.d("Photo", "KOSONG");

        }
//        mRecyclerView = root.findViewById(R.id.recycler_detail_trips);
//        mRecyclerView.setHasFixedSize(false);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mAdapter = new DetailTripsAdapter(detailTripItems);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public void getPhotoReference(String lokasi, final GoogleMapAPICallback listener){
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/" +
                "json?input=Tugu%Jogja&" +
                "inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        final String[] photoReference = {""};
        String reference = "KONToL";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("candidates");
                    JSONObject candidate = (JSONObject) jsonArray.get(0);
                    JSONArray photos = (JSONArray) candidate.get("photos");
                    JSONObject ref = (JSONObject) photos.get(0);
                    photoReference[0] = ref.getString("photo_reference");
                    Log.d("KONToL", "REF "+ ref.getString("photo_reference"));
                    Log.d("jsonn", "tolol");
                    NewTripActivity.ref = ref.getString("photo_reference");
                    listener.onPhotoReferenceCallback(ref.getString("photo_reference"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MASALAH", error.getMessage());
            }


        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
package com.example.tripkuy.tripssummary;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TripSummaryFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel homeViewModel;
    private Button btn_trip_baru, mBtnGmaps;
    public static com.example.tripkuy.models.service.Response response;
    String tgl_awal, tgl_akhir, email, penginapan;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    static String photoReference;
    GoogleMap map;
    MapView mapView;
    int day;
    double penginapanLat, penginapanLong;
    ConstraintLayout layout;
    private RecyclerView mRecyclerView;
    private DetailTripsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DetailTripItem> detailTripItems;
    BottomSheetBehavior bottomSheetBehavior;
    private TextView mDurasi, mHari, mJarak;
    private static RequestQueue mQueue;
    ItineraryDetailsItem itineraryDetail;
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
        mBtnGmaps = root.findViewById(R.id.btn_gmaps);
        mJarak = root.findViewById(R.id.jarak);
        layout = (ConstraintLayout) root.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapFrag2);

        mapFragment.getMapAsync(this);
        mQueue = Volley.newRequestQueue(getContext());
        buildRecyclerView();
        mBtnGmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String origin = response.data.origin.name;
                String stops ="";
                for (final RoutesItem item: itineraryDetail.routes) {
                    stops += "+to:"+item.destination.name;
                }
                final String uri = "http://maps.google.com/maps?daddr="+origin+stops;
//                        "+to:Lingampally+to:Nizampet+to:kukatpally+to:moosapet+to:Nizampet+to:srnagar+to:ameerpet+to:jubileehills+to:kothaguda";
                final Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        return root;

    }

    private void buildRecyclerView() {
        itineraryDetail = response.data.itineraryDetails.get(day);
        mDurasi.setText(itineraryDetail.totalDuration);
        mHari.setText("Hari "+itineraryDetail.day);
        mJarak.setText(itineraryDetail.totalDistance);
        detailTripItems = new ArrayList<>();
        for (final RoutesItem item: itineraryDetail.routes) {
            int resId = getResId("yogyakarta", R.drawable.class);
            detailTripItems.add(new DetailTripItem(resId,item.destination.name, item.tripDistance, item.tripDuration, item.id, item.rating));
        }
        mRecyclerView = root.findViewById(R.id.recycler_detail_trips);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new DetailTripsAdapter(detailTripItems, getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.addMarker(new
                MarkerOptions().position(
                new LatLng(response.data.origin.latLong.lat, response.data.origin.latLong.longg))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        );
        for (final RoutesItem item: itineraryDetail.routes) {
            map.addMarker(new MarkerOptions().position(new LatLng(item.destination.latLong.lat, item.destination.latLong.longg)));
        }
        LatLng latlongPengingapan = new LatLng(response.data.origin.latLong.lat, response.data.origin.latLong.longg);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlongPengingapan, 15));
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
    public void onDestroy() {
        super.onDestroy();
        map = null;
        System.gc();
    }
}
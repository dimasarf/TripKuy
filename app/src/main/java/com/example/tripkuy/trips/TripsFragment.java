package com.example.tripkuy.trips;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

import com.example.tripkuy.DetailTripActivity;
import com.example.tripkuy.Handler.RencanaHandler;
import com.example.tripkuy.Handler.TempatWisataHandler;
import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerAdapter.RecommendationAdapter;
import com.example.tripkuy.RecyclerAdapter.TripAdapter;
import com.example.tripkuy.RecyclerItems.TripsItem;
import com.example.tripkuy.interfaces.RencanaView;
import com.example.tripkuy.models.Rencana;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TripsFragment extends Fragment implements RencanaView {

    private TripsViewModel mViewModel;
    private Button btn_detail_trip;
    private RecyclerView mRecyclerView;
    private TripAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RencanaHandler rencanaHandler;
    List<Rencana> rencanaList;
    ArrayList<TripsItem> tripsItems = new ArrayList<>();
    View root;
    public static TripsFragment newInstance() {
        return new TripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.trips_fragment, container, false);

        TabHost tabs = (TabHost) root.findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Upcoming");

        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Past");
        tabs.addTab(spec);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        String personEmail = account.getEmail();
        rencanaHandler = new RencanaHandler(this, personEmail);
        rencanaHandler.getData();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onGetResult(List<Rencana> rencanas) {
        this.rencanaList = rencanas;
        for (Rencana item: rencanaList) {
            int resId = getResId(item.getDrawable(), R.drawable.class);

            tripsItems.add(new TripsItem(resId, item.getKota(), item.getTanggal_mulai(), item.getTanggal_akhir()));
        }

        mRecyclerView = root.findViewById(R.id.recycler_trip);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new TripAdapter(tripsItems);

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

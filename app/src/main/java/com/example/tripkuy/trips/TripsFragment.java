package com.example.tripkuy.trips;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;

import com.example.tripkuy.DetailTripActivity;
import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;

public class TripsFragment extends Fragment {

    private TripsViewModel mViewModel;
    private Button btn_detail_trip;
    public static TripsFragment newInstance() {
        return new TripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.trips_fragment, container, false);

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

        btn_detail_trip = (Button) root.findViewById(R.id.btn_detail_trip);
        btn_detail_trip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), DetailTripActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);
        // TODO: Use the ViewModel
    }

}

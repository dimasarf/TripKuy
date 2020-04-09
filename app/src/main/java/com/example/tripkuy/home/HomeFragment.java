package com.example.tripkuy.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.createtrip.CreateTripActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button btn_trip_baru;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btn_trip_baru = (Button) root.findViewById(R.id.btn_trip_baru);
        btn_trip_baru.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), CreateTripActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
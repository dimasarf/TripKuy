package com.example.tripkuy.createtrip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tripkuy.LoginActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.AgeListener;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.interfaces.PenginapanListener;
import com.example.tripkuy.interfaces.PengunjungListener;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.registration.PickDestinationFragment;
import com.example.tripkuy.registration.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class CreateTripActivity extends AppCompatActivity implements MoveFragmentListener, PenginapanListener, PengunjungListener {
    ViewPager viewPager;
    private double penginapanLat, penginapanLong;
    private String penginapan;
    private String _startDate;
    private String _endDate;
    private long _durasi;
    String pengungjung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        CreateTripPagerAdapter createTripPagerAdapter = new CreateTripPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.create_activity_view_pager);
        viewPager.setAdapter(createTripPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setVisibility(View.GONE);
        tabs.setupWithViewPager(viewPager);
        
    }

    public void checkBoxKegiatanOnClicked(View view) {
        String tag = "android:switcher:" + R.id.create_activity_view_pager + ":" + 2;
        ActivityFragment activityFragment= (ActivityFragment) getSupportFragmentManager().findFragmentByTag(tag);
        activityFragment.checkBoxKegiatanOnClicked(view);
    }

    @Override
    public void move(int position) {
        if(position == 2){
            String tag = "android:switcher:" + R.id.create_activity_view_pager + ":" + 2;
            ActivityFragment activityFragment= (ActivityFragment) getSupportFragmentManager().findFragmentByTag(tag);
            activityFragment.setPenginapan(penginapanLat, penginapanLong, penginapan,
                    _startDate, _endDate, _durasi);
            activityFragment.setPengungjung(pengungjung);
        }
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPenginapanListener(double lat, double longg, String penginapan, String startDate, String endDate, long durasi) {
        this.penginapanLat = lat;
        this.penginapanLong = longg;
        this.penginapan = penginapan;
        this._startDate = startDate;
        this._endDate = endDate;
        this._durasi = durasi;
    }

    @Override
    public void onPengunjungListener(String pengunjung) {
        this.pengungjung = pengunjung;
    }
}
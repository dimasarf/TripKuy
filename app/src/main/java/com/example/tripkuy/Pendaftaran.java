package com.example.tripkuy;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.registration.SectionsPagerAdapter;
import com.example.tripkuy.registration.UsiaFragment;
import com.google.android.material.tabs.TabLayout;

public class Pendaftaran extends AppCompatActivity implements MoveFragmentListener {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setVisibility(View.GONE);
        tabs.setupWithViewPager(viewPager);
        
    }

    @Override
    public void move(int position) {
        viewPager.setCurrentItem(position);
    }
}
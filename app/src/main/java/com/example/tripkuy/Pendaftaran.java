package com.example.tripkuy;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.interfaces.AgeListener;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.registration.PickDestinationFragment;
import com.example.tripkuy.registration.SectionsPagerAdapter;
import com.example.tripkuy.registration.UsiaFragment;
import com.google.android.material.tabs.TabLayout;

public class Pendaftaran extends AppCompatActivity implements MoveFragmentListener, AgeListener, FragmentGenderListener {
    ViewPager viewPager;
    Pengguna pengguna;
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        Intent intent = getIntent();
        String nama = intent.getStringExtra(LoginActivity.NAMA);
        String email = intent.getStringExtra(LoginActivity.EMAIL);
        String id_google = intent.getStringExtra(LoginActivity.ID);
        pengguna = new Pengguna();
        pengguna.setNama(nama);
        pengguna.setEmail(email);

        tabs = findViewById(R.id.tabs);
        tabs.setVisibility(View.GONE);
        tabs.setupWithViewPager(viewPager);
        
    }

    @Override
    public void move(int position) {
        if(position == 2){
            String tag = "android:switcher:" + R.id.view_pager + ":" + 2;
            PickDestinationFragment usiaFragment = (PickDestinationFragment) getSupportFragmentManager().findFragmentByTag(tag);
            usiaFragment.retrieve_pengguna(pengguna);
        }
        viewPager.setCurrentItem(position);
    }



    @Override
    public void ageListener(int age) {
        pengguna.setUsia(age);
    }

    @Override
    public void onFragmentGenderListener(String gender) {
        pengguna.setGender(gender);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabs = null;
        viewPager = null;
    }
}
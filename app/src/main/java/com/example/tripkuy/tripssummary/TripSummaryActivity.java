package com.example.tripkuy.tripssummary;

import android.content.Context;
import android.os.Bundle;
import android.os.strictmode.NonSdkApiUsedViolation;
import android.util.Log;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.R;
import com.example.tripkuy.registration.SectionsPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TripSummaryActivity extends AppCompatActivity {
    public  static com.example.tripkuy.models.service.Response response;
    private static Object TripSummaryActivity;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        TripSummaryFragment.response = response;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        TabLayout tab = findViewById(R.id.tabs);
        for (int i =0; i< response.data.itineraryDetails.size(); i++){
            tab.addTab(tab.newTab().setText("Hari " + (i+1)));
        }

        SummaryPageAdapter summaryPageAdapter = new SummaryPageAdapter(this, getSupportFragmentManager(), tab.getTabCount());
        viewPager = findViewById(R.id.summary_viewpager);
        viewPager.setAdapter(summaryPageAdapter);
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        if (tab.getTabCount() == 4) {
            tab.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        tab.bringToFront();

    }



}

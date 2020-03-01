package com.example.tripkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecommendationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
    }

    public void getDetailDestination(View view) {
        Intent intent = new Intent(this, DetailTempatWisataActivity.class);
        startActivity(intent);
    }
}

package com.example.tripkuy.registration2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tripkuy.R;

public class UsiaActivity extends AppCompatActivity {
    private TextView txtUsia;
    private Button btnNext;
    public static String USIA = "USIA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usia);

        txtUsia = findViewById(R.id.txt_usia);
        btnNext = findViewById(R.id.btn_next);
    }

    public void onNextClick(View view){
        Intent intent = new Intent(this, GenderActivity.class);
        String usia = txtUsia.getText().toString();
        intent.putExtra(USIA, usia);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        txtUsia = null;
        btnNext = null;
        super.onDestroy();
    }
}

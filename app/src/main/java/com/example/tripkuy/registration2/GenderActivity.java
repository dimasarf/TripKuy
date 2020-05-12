package com.example.tripkuy.registration2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.R;

public class GenderActivity extends AppCompatActivity {
    private Button btnNext, btnPrev;
    private RadioGroup radioGroup_gender;
    private String jenis_kelamin = "Bencong";
    private String usia;

    public static String TAG_USIA = "USIA";
    public static String TAG_KELAMIN = "KELAMIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        Intent intent = getIntent();
        usia = intent.getStringExtra(UsiaActivity.USIA);

        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        radioGroup_gender = findViewById(R.id.radio_group_gender);
        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radio_laki:
                        jenis_kelamin = "Laki-Laki";
                        break;
                    case R.id.radio_perempuan:
                        jenis_kelamin = "Perempuan";
                        break;
                }
            }
        });
    }

    public void onNextClick(View view){
        Intent intent = new Intent(this, PickPreferenceActivity.class);
        intent.putExtra(TAG_USIA, usia);
        intent.putExtra(TAG_KELAMIN, jenis_kelamin);
        startActivity(intent);
    }

    public void onPrevClick(View view){
        Intent intent = new Intent(this, UsiaActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onDestroy() {
        radioGroup_gender = null;
        super.onDestroy();
    }
}
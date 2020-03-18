package com.example.tripkuy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripkuy.models.Preferensi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewTripActivity extends AppCompatActivity {
    private static final String TAG = "TGLAWAL";
    public static final String DURASI = "DURASI";
    public static final String TGLAWAL = "TGLAWAL";
    public static final String TGLAKHIR = "TGLAKHIR";
    public static final String PARTNER = "PARTNER";
    public static final String KEGIATAN = "TGLAKHIR";

    EditText tgl_mulai, tgl_akhir;
    List<CheckBox> checkBoxPartner = new ArrayList<>();
    List<CheckBox> checkBoxKegiatan = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener tglMulaiListener;
    String kegiatan = "";
    String partner = "";
    long durasi;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        tgl_mulai = findViewById(R.id.txt_tanggal_mulai);
        tgl_akhir = findViewById(R.id.txt_tanggal_akhir);
        tgl_mulai.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(NewTripActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tgl_mulai.setText(i2 +"/"+i1+"/"+i);
                    }
                }, day, month, year);
                dpd.updateDate(year, month, day);
                dpd.show();
            }
        });

        tgl_akhir.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(NewTripActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tgl_akhir.setText(i2 +"/"+i1+"/"+i);
                    }
                }, day, month, year);
                dpd.updateDate(year, month, day);
                dpd.show();
            }
        });

        checkBoxPartner.add((CheckBox)findViewById(R.id.keluarga));
        checkBoxPartner.add((CheckBox)findViewById(R.id.pasangan));
        checkBoxPartner.add((CheckBox)findViewById(R.id.teman));
        checkBoxPartner.add((CheckBox)findViewById(R.id.sendiri));

        checkBoxKegiatan.add((CheckBox)findViewById(R.id.snorkeling));
        checkBoxKegiatan.add((CheckBox)findViewById(R.id.kuliner));
        checkBoxKegiatan.add((CheckBox)findViewById(R.id.petualang));
        checkBoxKegiatan.add((CheckBox)findViewById(R.id.belanja));
        checkBoxKegiatan.add((CheckBox)findViewById(R.id.foto));
        checkBoxKegiatan.add((CheckBox)findViewById(R.id.rafting));
    }

    public void searchDestination(View view) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        this.durasi =  2;
        Intent intent = new Intent(this, RecommendationActivity.class);
        intent.putExtra(TGLAWAL, tgl_mulai.getText().toString());
        intent.putExtra(TGLAKHIR, tgl_akhir.getText().toString());
        intent.putExtra(DURASI, durasi);

        startActivity(intent);
    }

    public void checkBoxPartnerOnClicked(View view){
        partner = "";
        for (CheckBox item : checkBoxPartner){
            if(item.isChecked()){
                String text= item.getText().toString();
                partner+= text+"";
            }
        }
        Toast.makeText(this,this.partner,Toast.LENGTH_SHORT).show();
    }

    public void checkBoxKegiatanOnClicked(View view){
        kegiatan = "";
        for (CheckBox item : checkBoxKegiatan){
            if(item.isChecked()){
                String text= item.getText().toString();
                kegiatan+= text+",";
            }
        }
        Toast.makeText(this,this.kegiatan,Toast.LENGTH_SHORT).show();
    }

}

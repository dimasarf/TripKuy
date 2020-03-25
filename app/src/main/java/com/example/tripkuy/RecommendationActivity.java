package com.example.tripkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripkuy.BackgroundWorker.AddRencana;
import com.example.tripkuy.Handler.TempatWisataHandler;
import com.example.tripkuy.RecyclerAdapter.RecommendationAdapter;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.interfaces.TempatWisataView;
import com.example.tripkuy.models.TempatWisata;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.Objects;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity implements TempatWisataView  {
    String durasi, tgl_awal, tgl_akhir, kegiatan, partner;
    TextView mDurasi, mTanggalMulai, mTanggalAkhir;

    private RecyclerView mRecyclerView;
    private RecommendationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    TempatWisataHandler tempatWisataHandler;
    List<TempatWisata> tempatWisatas;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    ArrayList<RecommendationItem> recommendationItems;

    public static final String TGLAWAL = "TGLAWAL";
    public static final String TGLAKHIR = "TGLAKHIR";
    public static final String EMAIL = "EMAIL";
    public static final String SELECTEDTEMPATWISATA = "TEMPATPILIHAN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Intent intent = getIntent();
        durasi = intent.getStringExtra(NewTripActivity.DURASI);
        tgl_awal = intent.getStringExtra(NewTripActivity.TGLAWAL);
        tgl_akhir = intent.getStringExtra(NewTripActivity.TGLAKHIR);
        kegiatan = intent.getStringExtra(NewTripActivity.KEGIATAN);
        partner = intent.getStringExtra(NewTripActivity.PARTNER);
        mDurasi = findViewById(R.id.txt_durasi);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateAwal =new SimpleDateFormat("dd/MM/yyyy").parse(tgl_awal);
            Date dateAkhir =new SimpleDateFormat("dd/MM/yyyy").parse(tgl_akhir);
            mTanggalMulai = findViewById(R.id.txt_tanggal_mulai);
            mTanggalAkhir = findViewById(R.id.txt_tanggal_akhir);
            mTanggalMulai.setText(dateFormat.format(dateAwal));
            mTanggalAkhir.setText(dateFormat.format(dateAkhir));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tempatWisataHandler = new TempatWisataHandler(this);
        tempatWisataHandler.getData();



    }

    public void getDetailDestination(View view) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        String personEmail = account.getEmail();
        Intent intent = new Intent(this, TripIteneraryActivity.class);
        intent.putExtra(TGLAWAL, mTanggalMulai.getText().toString());
        intent.putExtra(TGLAKHIR, mTanggalAkhir.getText().toString());
        intent.putExtra(EMAIL, personEmail);
        intent.putParcelableArrayListExtra(SELECTEDTEMPATWISATA, selectedTempatWisatas);
        startActivity(intent);
    }

    @Override
    public void onGetResult(final List<TempatWisata> tempatWisatas) {
        this.tempatWisatas = tempatWisatas;
        recommendationItems = new ArrayList<>();
        for (TempatWisata item : tempatWisatas) {
            int resId = getResId(item.getDrawable(), R.drawable.class);
            recommendationItems.add(new RecommendationItem(resId, item.getNama(), "5 K.M", item.getDrawable()));
        }

        mRecyclerView = findViewById(R.id.recycler_recomendation);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecommendationAdapter(recommendationItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setRecommendationListener(new RecommendationAdapter.onRecommendationItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSelect(int position) {
                selectedTempatWisatas.add(new TempatWisata(recommendationItems.get(position).getName(), recommendationItems.get(position).getImageString()));
                Log.d("TEST",recommendationItems.get(position).getName());
            }
        });

    }

    @Override
    public void onErrorLoading(String message) {

    }

    public int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

package com.example.tripkuy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.Handler.TempatWisataHandler;
import com.example.tripkuy.RecyclerAdapter.RecommendationAdapter;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.config.ApiServiceClient;
import com.example.tripkuy.interfaces.ApiServiceInterface;
import com.example.tripkuy.interfaces.TempatWisataView;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.dto.LatLongDTO;
import com.example.tripkuy.models.dto.PlaceDTO;
import com.example.tripkuy.models.dto.TripPlanDTO;
import com.example.tripkuy.models.service.Response;
import com.example.tripkuy.tripssummary.TripSummaryActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RecommendationActivity extends AppCompatActivity implements TempatWisataView {
    String  tgl_awal, tgl_akhir, kegiatan, partner, penginapan;
    TextView mDurasi, mTanggalMulai, mTanggalAkhir, mTxtPenginapan;
    long durasi;
    private RecyclerView mRecyclerView;
    private RecommendationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiServiceInterface apiServiceInterface;
    TempatWisataHandler tempatWisataHandler;
    List<TempatWisata> tempatWisatas;
    ArrayList<TempatWisata> selectedTempatWisatas = new ArrayList<>();
    ArrayList<RecommendationItem> recommendationItems;
    double penginapanLat, penginapanLong;
    public static final String TGLAWAL = "TGLAWAL";
    public static final String TGLAKHIR = "TGLAKHIR";
    public static final String EMAIL = "EMAIL";
    public static final String SELECTEDTEMPATWISATA = "TEMPATPILIHAN";
    public static final String ORIGIN = "ORIGIN";
    public static final String ORIGINLAT = "ORIGINLAT";
    public static final String ORIGINLONG= "ORIGINLONG";
    public static final String RESPONSE= "RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Intent intent = getIntent();
        durasi = intent.getLongExtra(NewTripActivity.DURASI, 1);
        tgl_awal = intent.getStringExtra(NewTripActivity.TGLAWAL);
        tgl_akhir = intent.getStringExtra(NewTripActivity.TGLAKHIR);
        kegiatan = intent.getStringExtra(NewTripActivity.KEGIATAN);
        partner = intent.getStringExtra(NewTripActivity.PARTNER);
        penginapan = intent.getStringExtra(NewTripActivity.ORIGIN);
        penginapanLat = intent.getDoubleExtra(NewTripActivity.ORIGINLAT, 0.0);
        penginapanLong = intent.getDoubleExtra(NewTripActivity.ORIGINLONG, 0.0);
        mDurasi = findViewById(R.id.txt_durasi);
        mDurasi.setText(durasi+" Hari");
        mTxtPenginapan = findViewById(R.id.txt_penginapan);
        mTxtPenginapan.setText(penginapan);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dateAwal = new SimpleDateFormat("dd/MM/yyyy").parse(tgl_awal);
            Date dateAkhir = new SimpleDateFormat("dd/MM/yyyy").parse(tgl_akhir);
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

    public void getDetailDestination(View view) throws ParseException {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        final String personEmail = account.getEmail();

        final Intent intent = new Intent(this, TripSummaryActivity.class);
        apiServiceInterface = ApiServiceClient.getApiClient().create(ApiServiceInterface.class);
        Call<Response> call = apiServiceInterface.process(generatePlan());
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    Log.d("Response", response.message());
                    Response data = response.body();
                    intent.putExtra(TGLAWAL, mTanggalMulai.getText().toString());
                    intent.putExtra(TGLAKHIR, mTanggalAkhir.getText().toString());
                    intent.putExtra(EMAIL, personEmail);
                    intent.putExtra(ORIGIN, penginapan);
                    intent.putExtra(ORIGINLAT, penginapanLat);
                    intent.putExtra(ORIGINLONG, penginapanLong);
                    intent.putParcelableArrayListExtra(SELECTEDTEMPATWISATA, selectedTempatWisatas);
                    TripSummaryActivity.response = data;
                    startActivity(intent);
                }
                else {
                    Log.d("Response", "ISI PESAN : " + response.message());
                    Log.d("Response", "KONTOL STATUS : " + response.code());
                }
                response.errorBody();
                Log.d("Response", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("Response Error", t.getMessage());
            }
        });


    }

    @Override
    public void onGetResult(final List<TempatWisata> tempatWisatas) {
        this.tempatWisatas = tempatWisatas;
        recommendationItems = new ArrayList<>();
        for (TempatWisata item : tempatWisatas) {
            int resId = getResId(item.getDrawable(), R.drawable.class);
            Log.d("KOORDINAT 2", item.getLatitude() + " - " + item.getLongitude());
            recommendationItems.add(new RecommendationItem(resId, item.getNama(), "5 K.M", item.getDrawable(), item.getLatitude(), item.getLongitude()));
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
                selectedTempatWisatas.add(new TempatWisata(recommendationItems.get(position).getName(), recommendationItems.get(position).getImageString(),
                        recommendationItems.get(position).getLatitude(), recommendationItems.get(position).getLongitude()));
                Log.d("KOORDINAT SELECTED", recommendationItems.get(position).getLatitude() + " - " + recommendationItems.get(position).getLongitude());
                Log.d("TEST", recommendationItems.get(position).getName());
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

    public TripPlanDTO generatePlan() throws ParseException {
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(tgl_awal);
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(tgl_akhir);
        PlaceDTO origin = new PlaceDTO("1", penginapan, penginapan, new LatLongDTO(penginapanLat, penginapanLong));
        ArrayList<PlaceDTO> destinations= new ArrayList<>();
        int i = 2;
        for (TempatWisata item : selectedTempatWisatas) {
            destinations.add(new PlaceDTO(String.valueOf(i), item.getNama(), item.getNama(), new LatLongDTO(Double.valueOf(item.getLatitude()), Double.valueOf(item.getLongitude()))));
            i++;
        }
        TripPlanDTO tripPlanDTO = new TripPlanDTO(startDate, endDate, durasi, origin, "Test", destinations);
        return  tripPlanDTO;
    }
}

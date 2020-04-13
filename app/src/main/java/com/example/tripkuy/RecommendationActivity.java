package com.example.tripkuy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.BackgroundWorker.AddRencana;
import com.example.tripkuy.Handler.TempatWisataHandler;
import com.example.tripkuy.RecyclerAdapter.RecommendationAdapter;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.config.ApiServiceClient;
import com.example.tripkuy.createtrip.ActivityFragment;
import com.example.tripkuy.interfaces.ApiServiceInterface;
import com.example.tripkuy.interfaces.TempatWisataView;
import com.example.tripkuy.models.TempatWisata;
import com.example.tripkuy.models.dto.LatLongDTO;
import com.example.tripkuy.models.dto.PlaceDTO;
import com.example.tripkuy.models.dto.TripPlanDTO;
import com.example.tripkuy.models.service.RecommenderAtribut;
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
    String pengungjung;
    private String personEmail;
    private ProgressDialog dialog;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        Intent intent = getIntent();
        durasi = intent.getLongExtra(ActivityFragment.DURASI, 1);
        tgl_awal = intent.getStringExtra(ActivityFragment.TGLAWAL);
        tgl_akhir = intent.getStringExtra(ActivityFragment.TGLAKHIR);
        kegiatan = intent.getStringExtra(ActivityFragment.KEGIATAN);
        pengungjung = intent.getStringExtra(ActivityFragment.PARTNER);
        penginapan = intent.getStringExtra(ActivityFragment.ORIGIN);
        penginapanLat = intent.getDoubleExtra(ActivityFragment.ORIGINLAT, 0.0);
        penginapanLong = intent.getDoubleExtra(ActivityFragment.ORIGINLONG, 0.0);
        mDurasi = findViewById(R.id.txt_durasi);
        mDurasi.setText(durasi+" Hari");
        mTxtPenginapan = findViewById(R.id.txt_penginapan);
        mTxtPenginapan.setText(penginapan);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        personEmail = account.getEmail();
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
        Log.d("pengunjung", "pengunjung " +pengungjung);

        tempatWisataHandler = new TempatWisataHandler(this, new RecommenderAtribut(pengungjung, kegiatan, durasi, personEmail), this);
        tempatWisataHandler.getData();
        dialog = new ProgressDialog(this);
    }

    public void getDetailDestination(View view) throws ParseException {

        final Intent intent = new Intent(this, TripSummaryActivity.class);
        apiServiceInterface = ApiServiceClient.getApiClient().create(ApiServiceInterface.class);
        Call<Response> call = apiServiceInterface.process(generatePlan());
        dialog.setMessage("Tunggu sebentar");
        dialog.show();
        context = this;
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
                    AddRencana addRencana = new AddRencana(context,data.data ,personEmail, intent);
                    addRencana.execute();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
//                    startActivity(intent);
                }
                else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT);
                    }
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
            recommendationItems.add(new RecommendationItem(item.getId(), resId, item.getNama(), item.getSimilarity(), item.getDrawable(), item.getLatitude(), item.getLongitude()));
        }

        mRecyclerView = findViewById(R.id.recycler_recomendation);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        mAdapter = new RecommendationAdapter(recommendationItems, this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setRecommendationListener(new RecommendationAdapter.onRecommendationItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onSelect(int position) {
                selectedTempatWisatas.add(new TempatWisata(recommendationItems.get(position).getId(), recommendationItems.get(position).getName(), recommendationItems.get(position).getImageString(),
                        recommendationItems.get(position).getLatitude(), recommendationItems.get(position).getLongitude()));
                Log.d("KOORDINAT SELECTED", recommendationItems.get(position).getLatitude() + " - " + recommendationItems.get(position).getLongitude());
                Log.d("TESTID", "id tempat "+recommendationItems.get(position).getId());
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

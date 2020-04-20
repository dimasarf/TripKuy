package com.example.tripkuy.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.Dashboard;
import com.example.tripkuy.R;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.interfaces.KategoriPenggunaListener;
import com.example.tripkuy.models.Pengguna;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKategoriActivity extends AppCompatActivity implements KategoriPenggunaListener {
    ApiPenggunaInterface apiPenggunaInterface;
    private TextView mKategoriPengguna;
    List<CheckBox> items = new ArrayList<CheckBox>();
    KategoriPenggunaListener listener;
    Button mBtn_selesai;
    private ProgressDialog dialog;
    String kategori = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        listener = this;
        mKategoriPengguna = findViewById(R.id.kategori_pengguna);
        mBtn_selesai = findViewById(R.id.btn_selesai);
        buildCheckBoxes();
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        apiPenggunaInterface = ApiClient.getApiClient().create(ApiPenggunaInterface.class);
        Call<String> call = apiPenggunaInterface.getKategori(account.getEmail());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Tunggu sebentar");
        dialog.show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                listener.kategoriPenggunaListener(response.body());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        mBtn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kategori = "";
                dialog.setMessage("Tunggu sebentar");
                dialog.show();
                for (CheckBox item:items) {
                    if(item.isChecked()){
                        String text= item.getText().toString();
                        if(kategori == "")
                            kategori += text;
                        else
                            kategori += ","+text;
                    }
                }
                mKategoriPengguna.setText(kategori);
                Call<Pengguna> call = apiPenggunaInterface.updateKategori(account.getEmail(), kategori);
                call.enqueue(new Callback<Pengguna>() {
                    @Override
                    public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
                        if(response.isSuccessful() && response.body() != null){
                            Boolean success = response.body().getSuccess();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            if(success){
                                Toast.makeText(getApplicationContext(),
                                        "Perubahan kategori berhasil disimpan!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Pengguna> call, Throwable t) {

                    }
                });
            }

        });

    }

    public void buildCheckBoxes(){
        items.add((CheckBox)findViewById(R.id.pref_airterjun));
        items.add((CheckBox)findViewById(R.id.pref_bukit));
        items.add((CheckBox)findViewById(R.id.pref_goa));
        items.add((CheckBox)findViewById(R.id.pref_gurun));
        items.add((CheckBox)findViewById(R.id.pref_kebun));
        items.add((CheckBox)findViewById(R.id.pref_makam));
        items.add((CheckBox)findViewById(R.id.pref_museum));
        items.add((CheckBox)findViewById(R.id.pref_telaga));
        items.add((CheckBox)findViewById(R.id.pref_waduk));
        items.add((CheckBox)findViewById(R.id.pref_waterboom));
        items.add((CheckBox)findViewById(R.id.pref_pemandian));
        items.add((CheckBox)findViewById(R.id.pref_zoo));
        items.add((CheckBox)findViewById(R.id.pref_lembah));
        items.add((CheckBox)findViewById(R.id.pref_pantai));
        items.add((CheckBox)findViewById(R.id.pref_park));
        items.add((CheckBox)findViewById(R.id.pref_gunung));
        items.add((CheckBox)findViewById(R.id.pref_garden));
        items.add((CheckBox)findViewById(R.id.pref_sungai));
        items.add((CheckBox)findViewById(R.id.pref_monumen));
        items.add((CheckBox)findViewById(R.id.pref_galery));
        items.add((CheckBox)findViewById(R.id.pref_candi));
        items.add((CheckBox)findViewById(R.id.pref_creative));
        items.add((CheckBox)findViewById(R.id.pref_hiburan));
        items.add((CheckBox)findViewById(R.id.pref_sains));
        items.add((CheckBox)findViewById(R.id.pref_pasar));
        items.add((CheckBox)findViewById(R.id.pref_desa));
    }

    public void getSelectedKategori(){
        String[] kategoris = kategori.split(",");
        for (CheckBox item:items) {
            Log.d("selected", "kategori "+item.getText().toString());
            if(Arrays.asList(kategoris).contains(item.getText().toString()))
                item.setChecked(true);
        }
    }

    @Override
    public void kategoriPenggunaListener(String kategori) {
        this.kategori = kategori;
        mKategoriPengguna.setText(this.kategori);
        getSelectedKategori();
    }
}

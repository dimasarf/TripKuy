package com.example.tripkuy.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripkuy.R;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.interfaces.KategoriPenggunaListener;
import com.example.tripkuy.models.Pengguna;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProfileActivity extends AppCompatActivity {
    private TextView mNama, mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        mNama = findViewById(R.id.nama);
        mEmail = findViewById(R.id.email);
        mNama.setText(account.getDisplayName());
        mEmail.setText(account.getEmail());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNama = null;
        mEmail = null;
        System.gc();
    }
}


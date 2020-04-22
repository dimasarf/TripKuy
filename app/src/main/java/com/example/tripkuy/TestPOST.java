package com.example.tripkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.models.Pengguna;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestPOST extends AppCompatActivity {

    ApiPenggunaInterface apiPenggunaInterface;

    private Button btn_selesai, btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_post);

        btn_selesai = (Button) findViewById(R.id.btn_selesai);
        btn_selesai.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0) {
                class AddEmployee extends AsyncTask<Void, Void, Void> {
                    AlertDialog alertDialog;
                    ProgressDialog progressDialog;
                    Context context;

                    AddEmployee(Context ctx){
                        context = (Context) ctx;
                        progressDialog = new ProgressDialog(ctx);

                    }
                    @Override
                    protected void onPreExecute() {
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                    }

                    @Override
                    protected void onPostExecute(Void s) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    protected Void doInBackground(Void... params) {

                        return null;
                    }
                }

                AddEmployee ae = new AddEmployee(TestPOST.this);
                ae.execute();
            }
        });
    }
}

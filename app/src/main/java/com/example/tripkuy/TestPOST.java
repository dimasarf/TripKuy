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

public class TestPOST extends AppCompatActivity {
    private Button btn_selesai, btnPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_post);
        btn_selesai = (Button) findViewById(R.id.btn_selesai);
        btn_selesai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                class AddEmployee extends AsyncTask<String, Void, String> {
                    AlertDialog alertDialog;
                    Context context;

                    AddEmployee(Context ctx){
                        context = (Context) ctx;
                    }
                    @Override
                    protected void onPreExecute() {
//                        super.onPreExecute();
                        alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("POST STATUS");
                    }

                    @Override
                    protected void onPostExecute(String s) {
//                        super.onPostExecute(s);
                        alertDialog.setMessage(s);

                    }

                    @Override
                    protected String doInBackground(String... params) {
                        String URL_ADD_PENGGUNA = "https://tripkuy.000webhostapp.com/save_pengguna.php";
                        String result = "";
                        try {
                            URL url = new URL(URL_ADD_PENGGUNA);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                            String post_data =
                                    URLEncoder.encode("nama", "UTF-8") + "=" + URLEncoder.encode("DIMASTYO", "UTF-8") + "&" +
                                            URLEncoder.encode("usia", "UTF-8") + "=" + URLEncoder.encode("22", "UTF-8") + "&" +
                                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode("test@mail.com", "UTF-8") + "&"+
                                            URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode("LAKI", "UTF-8");
                            bufferedWriter.write(post_data);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            outputStream.close();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                            String line = "";
                            while ((line = bufferedReader.readLine()) != null){
                                result += line;
                            }
                            bufferedReader.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return result;
                    }
                }

                AddEmployee ae = new AddEmployee(TestPOST.this);
                ae.execute();
//                Intent intent = new Intent(getActivity(), Dashboard.class);
//                startActivity(intent);
            }
        });
    }
}

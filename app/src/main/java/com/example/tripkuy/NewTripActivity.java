package com.example.tripkuy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.interfaces.GoogleMapAPICallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewTripActivity extends AppCompatActivity {
    private static final String TAG = "TGLAWAL";
    public static final String DURASI = "DURASI";
    public static final String TGLAWAL = "TGLAWAL";
    public static final String TGLAKHIR = "TGLAKHIR";
    public static final String PARTNER = "PARTNER";
    public static final String KEGIATAN = "TGLAKHIR";
    public static final String ORIGIN = "ORIGIN";
    public static final String ORIGINLAT = "ORIGINLAT";
    public static final String ORIGINLONG= "ORIGINLONG";
    public static String ref;
    double penginapanLat, penginapanLong;
    EditText tgl_mulai, tgl_akhir;
    List<CheckBox> checkBoxPartner = new ArrayList<>();
    List<CheckBox> checkBoxKegiatan = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener tglMulaiListener;
    SearchView searchView;
    String kegiatan = "";
    String partner = "";
    long durasi;
    String penginapan;
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
                        tgl_mulai.setText(i2 + "/" + i1 + "/" + i);
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
                        tgl_akhir.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, day, month, year);
                dpd.updateDate(year, month, day);
                dpd.show();
            }
        });


        checkBoxPartner.add((CheckBox) findViewById(R.id.keluarga));
        checkBoxPartner.add((CheckBox) findViewById(R.id.pasangan));
        checkBoxPartner.add((CheckBox) findViewById(R.id.teman));
        checkBoxPartner.add((CheckBox) findViewById(R.id.sendiri));

        checkBoxKegiatan.add((CheckBox) findViewById(R.id.snorkeling));
        checkBoxKegiatan.add((CheckBox) findViewById(R.id.kuliner));
        checkBoxKegiatan.add((CheckBox) findViewById(R.id.petualang));
        checkBoxKegiatan.add((CheckBox) findViewById(R.id.belanja));
        checkBoxKegiatan.add((CheckBox) findViewById(R.id.foto));
        checkBoxKegiatan.add((CheckBox) findViewById(R.id.rafting));

        Places.initialize(getApplicationContext(), "AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc");
        PlacesClient placesClient = Places.createClient(this);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.


                penginapan = place.getName();
                LatLng queriedLocation = place.getLatLng();
                penginapanLat = queriedLocation.latitude;
                penginapanLong = queriedLocation.longitude;
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }

    public void searchDestination(View view) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        this.durasi = 2;
        Intent intent = new Intent(this, RecommendationActivity.class);
        intent.putExtra(TGLAWAL, tgl_mulai.getText().toString());
        intent.putExtra(TGLAKHIR, tgl_akhir.getText().toString());
        intent.putExtra(DURASI, durasi);
        intent.putExtra(ORIGIN, penginapan);
        intent.putExtra(ORIGINLAT, penginapanLat);
        intent.putExtra(ORIGINLONG, penginapanLong);

        startActivity(intent);
    }

    public void checkBoxPartnerOnClicked(View view) {
        partner = "";
        for (CheckBox item : checkBoxPartner) {
            if (item.isChecked()) {
                String text = item.getText().toString();
                partner += text + "";
            }
        }
        Toast.makeText(this, this.partner, Toast.LENGTH_SHORT).show();
        final String[] photo = {""};
        getPhotoReference("tugu jogja", new GoogleMapAPICallback() {
            @Override
            public void onPhotoReferenceCallback(String photoReference) {
                photo[0] = photoReference;
                Log.d("photoRef", photoReference);
            }
        });
        Log.d("respodd", "json tolol");
    }

    public void checkBoxKegiatanOnClicked(View view) {
        kegiatan = "";
        for (CheckBox item : checkBoxKegiatan) {
            if (item.isChecked()) {
                String text = item.getText().toString();
                kegiatan += text + ",";
            }
        }
        Toast.makeText(this, this.kegiatan, Toast.LENGTH_SHORT).show();
    }

    public void getPhotoReference(String lokasi, final GoogleMapAPICallback listener){
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/" +
                "json?input=Tugu%Jogja&" +
                "inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        final String[] photoReference = {""};
        String reference = "KONToL";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("candidates");
                    JSONObject candidate = (JSONObject) jsonArray.get(0);
                    JSONArray photos = (JSONArray) candidate.get("photos");
                    JSONObject ref = (JSONObject) photos.get(0);
                    photoReference[0] = ref.getString("photo_reference");
                    Log.d("KONToL", "REF "+ ref.getString("photo_reference"));
                    Log.d("jsonn", "tolol");
                    NewTripActivity.ref = ref.getString("photo_reference");
                    listener.onPhotoReferenceCallback(ref.getString("photo_reference"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MASALAH", error.getMessage());
            }


        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}

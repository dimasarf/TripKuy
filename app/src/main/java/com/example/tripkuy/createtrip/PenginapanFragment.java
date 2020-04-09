package com.example.tripkuy.createtrip;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.AgeListener;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.interfaces.PenginapanListener;
import com.example.tripkuy.models.Pengguna;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class PenginapanFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "TGLAWAL";
    double penginapanLat, penginapanLong;
    String penginapan;
    EditText tgl_mulai, tgl_akhir;
    PenginapanListener penginapanListener;


    private OnFragmentInteractionListener mListener;
    Button btnNext;
    MoveFragmentListener movementListener;





    public PenginapanFragment() {
        // Required empty public constructor
    }
    public static PenginapanFragment newInstance(String param1, String param2) {
        PenginapanFragment fragment = new PenginapanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_penginapan, container, false);
        btnNext = root.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            private long durasi;

            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date dateAwal = formatter.parse(tgl_mulai.getText().toString());
                    Date dateAkhir = formatter.parse(tgl_akhir.getText().toString());
                    long diffInMillies = Math.abs(dateAkhir.getTime() - dateAwal.getTime());
                    this.durasi = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    penginapanListener.onPenginapanListener(penginapanLat, penginapanLong, penginapan,
                            tgl_mulai.getText().toString(), tgl_akhir.getText().toString(), durasi);
                    movementListener.move(1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

        tgl_mulai = root.findViewById(R.id.txt_tanggal_mulai);
        tgl_akhir = root.findViewById(R.id.txt_tanggal_akhir);

        tgl_mulai.setShowSoftInputOnFocus(false);
        tgl_mulai.setInputType(InputType.TYPE_NULL);
        tgl_mulai.setFocusable(false);

        tgl_akhir.setShowSoftInputOnFocus(false);
        tgl_akhir.setInputType(InputType.TYPE_NULL);
        tgl_akhir.setFocusable(false);

        tgl_mulai.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tgl_mulai.setText(i2 + "/" + (i1+1) + "/" + i);
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

                DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tgl_akhir.setText(i2 + "/" + (i1+1) + "/" + i);
                    }
                }, day, month, year);
                dpd.updateDate(year, month, day);
                dpd.show();
            }
        });


        Places.initialize(getContext(), "AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc");
        PlacesClient placesClient = Places.createClient(getContext());

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Cari lokasi penginapan anda");
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

        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MoveFragmentListener) {
            movementListener = (MoveFragmentListener) context;
            penginapanListener = (PenginapanListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

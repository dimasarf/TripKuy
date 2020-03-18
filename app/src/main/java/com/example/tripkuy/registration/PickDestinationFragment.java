package com.example.tripkuy.registration;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tripkuy.BackgroundWorker.AddPengguna;
import com.example.tripkuy.Dashboard;
import com.example.tripkuy.R;
import com.example.tripkuy.TestPOST;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiPenggunaInterface;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.models.Pengguna;
import com.example.tripkuy.models.Preferensi;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickDestinationFragment extends Fragment {


    ApiPenggunaInterface apiPenggunaInterface;
    MoveFragmentListener listener;
    List<Preferensi> preferensiList = new ArrayList<Preferensi>();

    private Button btn_selesai, btnPrev;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Pengguna pengguna;

    private OnFragmentInteractionListener mListener;


    public static PickDestinationFragment newInstance(String param1, String param2) {
        PickDestinationFragment fragment = new PickDestinationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_pick_destination, container, false);
        final List<CheckBox> items = new ArrayList<CheckBox>();
        items.add((CheckBox)rootView.findViewById(R.id.pref_prambanan));
        items.add((CheckBox)rootView.findViewById(R.id.pref_malioboro));
        items.add((CheckBox)rootView.findViewById(R.id.pref_parangtritis));
        items.add((CheckBox)rootView.findViewById(R.id.pref_tamansari));
        items.add((CheckBox)rootView.findViewById(R.id.pref_tugu));
        items.add((CheckBox)rootView.findViewById(R.id.pref_breksi));
        items.add((CheckBox)rootView.findViewById(R.id.pref_monjali));
        items.add((CheckBox)rootView.findViewById(R.id.pref_paralayang));
        items.add((CheckBox)rootView.findViewById(R.id.pref_kalibiru));

        btn_selesai = (Button) rootView.findViewById(R.id.btn_selesai);
        btnPrev = (Button) rootView.findViewById(R.id.btn_prev);
        btn_selesai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                for (CheckBox item : items){
                    if(item.isChecked()){
                        String text= item.getText().toString();
                        preferensiList.add(new Preferensi(text));
                    }

                }
                AddPengguna addPengguna = new AddPengguna(getActivity(), pengguna, preferensiList);
                addPengguna.execute();
                Intent intent = new Intent(getActivity(), Dashboard.class);
                startActivity(intent);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.move(1);
            }
        });
        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else if (context instanceof MoveFragmentListener) {
            listener = (MoveFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void retrieve_pengguna(Pengguna _pengguna) {
        pengguna = _pengguna;
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

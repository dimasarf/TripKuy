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
    String preferensi = "";
    List<CheckBox> items = new ArrayList<CheckBox>();
    private Button btn_selesai, btnPrev;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Pengguna pengguna;
    AddPengguna addPengguna;
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
       buildCheckBoxes(rootView);

        btn_selesai = (Button) rootView.findViewById(R.id.btn_selesai);
        btnPrev = (Button) rootView.findViewById(R.id.btn_prev);
        btn_selesai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                for (CheckBox item : items){
                    if(item.isChecked()){
                        String text= item.getText().toString();
                        if(preferensi == "")
                            preferensi += text;
                        else
                            preferensi += ","+text;
                    }

                }
                pengguna.setPreferensi(preferensi);
                addPengguna = new AddPengguna(getActivity(), pengguna);
                addPengguna.execute();

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
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void buildCheckBoxes(View rootView){
        items.add((CheckBox)rootView.findViewById(R.id.pref_airterjun));
        items.add((CheckBox)rootView.findViewById(R.id.pref_bukit));
        items.add((CheckBox)rootView.findViewById(R.id.pref_goa));
        items.add((CheckBox)rootView.findViewById(R.id.pref_gurun));
        items.add((CheckBox)rootView.findViewById(R.id.pref_kebun));
        items.add((CheckBox)rootView.findViewById(R.id.pref_makam));
        items.add((CheckBox)rootView.findViewById(R.id.pref_museum));
        items.add((CheckBox)rootView.findViewById(R.id.pref_telaga));
        items.add((CheckBox)rootView.findViewById(R.id.pref_waduk));
        items.add((CheckBox)rootView.findViewById(R.id.pref_waterboom));
        items.add((CheckBox)rootView.findViewById(R.id.pref_pemandian));
        items.add((CheckBox)rootView.findViewById(R.id.pref_zoo));
        items.add((CheckBox)rootView.findViewById(R.id.pref_lembah));
        items.add((CheckBox)rootView.findViewById(R.id.pref_pantai));
        items.add((CheckBox)rootView.findViewById(R.id.pref_park));
        items.add((CheckBox)rootView.findViewById(R.id.pref_gunung));
        items.add((CheckBox)rootView.findViewById(R.id.pref_garden));
        items.add((CheckBox)rootView.findViewById(R.id.pref_sungai));
        items.add((CheckBox)rootView.findViewById(R.id.pref_monumen));
        items.add((CheckBox)rootView.findViewById(R.id.pref_galery));
        items.add((CheckBox)rootView.findViewById(R.id.pref_candi));
        items.add((CheckBox)rootView.findViewById(R.id.pref_creative));
        items.add((CheckBox)rootView.findViewById(R.id.pref_hiburan));
        items.add((CheckBox)rootView.findViewById(R.id.pref_sains));
        items.add((CheckBox)rootView.findViewById(R.id.pref_pasar));
        items.add((CheckBox)rootView.findViewById(R.id.pref_desa));
    }

    @Override
    public void onDestroy() {
        addPengguna.cancel(true);
        items.clear();
        System.gc();
        super.onDestroy();

    }
}

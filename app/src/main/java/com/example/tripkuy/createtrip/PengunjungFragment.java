package com.example.tripkuy.createtrip;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.interfaces.PengunjungListener;
import com.example.tripkuy.models.Pengguna;

public class PengunjungFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;
    Button btnNext, btnPrev;

    MoveFragmentListener movementListener;
    String pengungjung = "";
    Pengguna pengguna;
    private RadioGroup radioGroup_pengunjung;
    PengunjungListener pengunjungListener;



    public PengunjungFragment() {
        // Required empty public constructor
    }
    public static PengunjungFragment newInstance(String param1, String param2) {
        PengunjungFragment fragment = new PengunjungFragment();

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
        View root = inflater.inflate(R.layout.fragment_pengunjung, container, false);
        pengguna = new Pengguna();
        btnNext = root.findViewById(R.id.btn_next);
        btnPrev = root.findViewById(R.id.btn_prev);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pengunjungListener.onPengunjungListener(pengungjung);
                movementListener.move(2);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movementListener.move(0);
            }
        });

        radioGroup_pengunjung = root.findViewById(R.id.radio_group_pengunjung);
        radioGroup_pengunjung.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.keluarga:
                        pengungjung = "elder people";
                        break;
                    case R.id.pasangan:
                        pengungjung = "couple";
                        break;
                    case R.id.teman:
                        pengungjung = "mates";
                        break;
                    case R.id.sendiri:
                        pengungjung = "solo-travel";
                        break;
                    case R.id.anak:
                        pengungjung = "kids friendly";
                        break;
                }

            }
        });
        Log.d("partner", "partner"+pengungjung);
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
            pengunjungListener = (PengunjungListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        movementListener  = null;
        pengunjungListener  = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

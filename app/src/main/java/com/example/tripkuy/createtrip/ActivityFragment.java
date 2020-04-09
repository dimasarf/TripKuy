package com.example.tripkuy.createtrip;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.tripkuy.RecommendationActivity;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.models.Pengguna;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TGLAWAL";
    public static final String DURASI = "DURASI";
    public static final String TGLAWAL = "TGLAWAL";
    public static final String TGLAKHIR = "TGLAKHIR";
    public static final String PARTNER = "PARTNER";
    public static final String KEGIATAN = "TGLAKHIR";
    public static final String ORIGIN = "ORIGIN";
    public static final String ORIGINLAT = "ORIGINLAT";
    public static final String ORIGINLONG= "ORIGINLONG";
    List<CheckBox> checkBoxKegiatan = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button btnPrev, btnCari;
    private RadioGroup radioGroup_gender;
    private RadioButton radioButton;

    MoveFragmentListener movementListener;
    FragmentGenderListener genderListener;
    Pengguna pengguna;
    private double penginapanLat, penginapanLong;
    private String penginapan;
    private String _startDate;
    private String _endDate;
    private long _durasi;
    String pengungjung;
    String kegiatan = "";




    public ActivityFragment() {
        // Required empty public constructor
    }
    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_activity, container, false);
        btnPrev = root.findViewById(R.id.btn_prev);
        btnCari = root.findViewById(R.id.btn_cari_trip);
        buildCheckBoxes(root);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movementListener.move(1);
            }
        });
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecommendationActivity.class);
                intent.putExtra(TGLAWAL, _startDate);
                intent.putExtra(TGLAKHIR, _endDate);
                intent.putExtra(DURASI, _durasi);
                intent.putExtra(ORIGIN, penginapan);
                intent.putExtra(ORIGINLAT, penginapanLat);
                intent.putExtra(ORIGINLONG, penginapanLong);

                startActivity(intent);
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
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setPenginapan(double lat, double longg, String penginapan, String startDate, String endDate, long durasi){
        this.penginapanLat = lat;
        this.penginapanLong = longg;
        this.penginapan = penginapan;
        this._startDate = startDate;
        this._endDate = endDate;
        this._durasi = durasi;
    }

    public void setPengungjung(String pengunjung){
        this.pengungjung = pengunjung;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void checkBoxKegiatanOnClicked(View view) {
        kegiatan = "";
        for (CheckBox item : checkBoxKegiatan) {
            if (item.isChecked()) {
                String text = item.getText().toString();
                if(kegiatan == "")
                    kegiatan += text;
                else
                    kegiatan += ","+text;
            }
        }
        Toast.makeText(getContext(), this.kegiatan, Toast.LENGTH_SHORT).show();
    }

    public void buildCheckBoxes(View root){
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.mendaki));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.berenang));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.goa));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.jogging));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.kemah));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.kebun));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.berburu));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.mancing));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.main));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.keliling));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.outsport));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.rekreasi));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.trekking));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.seni));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.keindahan));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.sejarah));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.berendam));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.foto));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.makanminum));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.santai));
        checkBoxKegiatan.add((CheckBox) root.findViewById(R.id.sandi));
    }
}

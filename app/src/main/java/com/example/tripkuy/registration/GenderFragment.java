package com.example.tripkuy.registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.AgeListener;
import com.example.tripkuy.interfaces.FragmentGenderListener;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.models.Pengguna;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GenderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button btnNext, btnPrev;
    private RadioGroup radioGroup_gender;
    private RadioButton radioButton;

    MoveFragmentListener movementListener;
    FragmentGenderListener genderListener;
    Pengguna pengguna;





    public GenderFragment() {
        // Required empty public constructor
    }
    public static GenderFragment newInstance(String param1, String param2) {
        GenderFragment fragment = new GenderFragment();
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
        View root = inflater.inflate(R.layout.fragment_gender, container, false);
        pengguna = new Pengguna();
        btnNext = root.findViewById(R.id.btn_next);
        btnPrev = root.findViewById(R.id.btn_prev);
        radioGroup_gender = root.findViewById(R.id.radio_group_gender);
        final String[] jenis_kelamin = {""};
        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.radio_laki:
                        jenis_kelamin[0] = "Laki-Laki";
                        break;
                    case R.id.radio_perempuan:
                        jenis_kelamin[0] = "Perempuan";
                        break;
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genderListener.onFragmentGenderListener(jenis_kelamin[0]);
                movementListener.move(2);
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movementListener.move(0);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else if (context instanceof MoveFragmentListener) {
            genderListener = (FragmentGenderListener) context;
            movementListener = (MoveFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

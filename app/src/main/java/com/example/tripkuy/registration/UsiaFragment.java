package com.example.tripkuy.registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tripkuy.R;
import com.example.tripkuy.interfaces.MoveFragmentListener;
import com.example.tripkuy.ui.main.PageViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsiaFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    MoveFragmentListener listener;

    private PageViewModel pageViewModel;
    Button btnNext;

    public static UsiaFragment newInstance(int index) {
        UsiaFragment fragment = new UsiaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MoveFragmentListener) {
            listener = (MoveFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Must implement listener sign up data");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_usia, container, false);
        btnNext = root.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.move(1);
            }
        });
        return root;
    }
}
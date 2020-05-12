package com.example.tripkuy;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tripkuy.RecyclerAdapter.LocationAutoCompleteAdapter;
import com.example.tripkuy.config.ApiClient;
import com.example.tripkuy.interfaces.ApiTempatWisataInterface;
import com.example.tripkuy.interfaces.LocationListener;
import com.example.tripkuy.models.TempatWisata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDestinationDialog extends AppCompatDialogFragment implements  LocationListener {
    AddDestinationDialogListener listener;
    protected static View view = null;
    private AutoCompleteTextView search;
    public static TempatWisata tempatWisata;
    private ArrayList<TempatWisata> tempatWisataList;
    ApiTempatWisataInterface apiTempatWisataInterface;
    private LocationListener listnerLocation;
    private LocationAutoCompleteAdapter adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        listnerLocation = this;
        view = inflater.inflate(R.layout.layout_add_destination_dialog, null);
        search =  view.findViewById(R.id.search_wisatas);

        getLocations();
        builder.setView(view).setTitle("Cari Destinasi Tambahan")
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Tambah Destinasi", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("tempat4", "tempat " + tempatWisata.getNama());
                        listener.onDialogListener(tempatWisata);
                    }
                });
        return builder.create();
    }

    public void getLocations(){
        apiTempatWisataInterface = ApiClient.getApiClient().create(ApiTempatWisataInterface.class);
        Call call = apiTempatWisataInterface.get_locations();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("size", "KONTOL");
                listnerLocation.locationListner((ArrayList<TempatWisata>) response.body());

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AddDestinationDialogListener) context;
    }

    @Override
    public void locationListner(ArrayList<TempatWisata> list) {
        tempatWisataList = list;
        tempatWisataList.size();
        adapter = new LocationAutoCompleteAdapter(getContext(), tempatWisataList);
        search.setAdapter(adapter);
        Log.d("size", "KONTOLL "+ tempatWisataList.size());
    }

    public interface AddDestinationDialogListener {
        void onDialogListener(TempatWisata tempatWisata);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}

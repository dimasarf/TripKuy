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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tripkuy.models.TempatWisata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AddDestinationDialog extends AppCompatDialogFragment {

    double destinasiLat, destinasiLong;
    String destinasi;
    AddDestinationDialogListener listener;
    protected static View view = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_add_destination_dialog, null);

        Places.initialize(getContext(), "AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc");
        PlacesClient placesClient = Places.createClient(getContext());

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment) getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setHint("Cari lokasi penginapan anda");
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            private static final String TAG = "";

            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.


                destinasi = place.getName();
                LatLng queriedLocation = place.getLatLng();
                destinasiLat = queriedLocation.latitude;
                destinasiLong = queriedLocation.longitude;
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

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
                        Log.d("tempat4", "tempat " + destinasi);
                        TempatWisata tempatWisata = new TempatWisata("", destinasi, "",
                                String.valueOf(destinasiLat), String.valueOf(destinasiLong));
                        listener.onDialogListener(tempatWisata);
                        FragmentTransaction ft2 = getFragmentManager()
                                .beginTransaction();
                        ft2.remove((AutocompleteSupportFragment) getActivity()
                                .getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment));
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AddDestinationDialogListener) context;
    }

    public interface AddDestinationDialogListener {
        void onDialogListener(TempatWisata tempatWisata);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.autocomplete_fragment));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

}

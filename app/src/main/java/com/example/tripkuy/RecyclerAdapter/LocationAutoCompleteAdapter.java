package com.example.tripkuy.RecyclerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripkuy.AddDestinationDialog;
import com.example.tripkuy.R;
import com.example.tripkuy.models.TempatWisata;

import java.util.ArrayList;
import java.util.List;

public class LocationAutoCompleteAdapter extends ArrayAdapter<TempatWisata> {
    private List<TempatWisata> tempatWisataListFull;
    List<TempatWisata> suggestion = new ArrayList<>();

    public LocationAutoCompleteAdapter(@NonNull Context context, @NonNull List<TempatWisata> tempatWisataList) {
        super(context, 0, tempatWisataList);
        this.tempatWisataListFull = new ArrayList<>(tempatWisataList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tempatwisata_autocomplete, parent, false);
        }

        TextView txtNama = convertView.findViewById(R.id.tempat);

        TempatWisata tempat = getItem(position);
        if(tempat != null){
            Log.d("tempat6", "tempat " +position+ " "+tempat.getNama());
            txtNama.setText(tempat.getNama());
            AddDestinationDialog.tempatWisata = getItem(position);
        }

        return  convertView;
    }
    @Override
    public Filter getFilter() {
        return tempatFilter;
    }

    @Nullable
    @Override
    public TempatWisata getItem(int position) {
        AddDestinationDialog.tempatWisata = suggestion.get(position);
        return suggestion.get(position);
    }

    private Filter tempatFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            suggestion.clear();
            if(charSequence == null || charSequence.length() == 0){
                suggestion.addAll(tempatWisataListFull);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (TempatWisata item : tempatWisataListFull) {
                    if(item.getNama().toLowerCase().contains(filterPattern)){
                        suggestion.add(item);
                    }
                }
            }

            results.values = suggestion;
            results.count = suggestion.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List)filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

            Log.d("tempat5", "tempat "+((TempatWisata)resultValue).getNama());
            return ((TempatWisata)resultValue).getNama();
        }
    };
}

package com.example.tripkuy.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.RecyclerItems.TripsItem;
import com.example.tripkuy.models.Rencana;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripItemViewHolder> {

    private ArrayList<TripsItem> tripsItems;

    public static class TripItemViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imgTempat;
        public TextView txtTempat, txtTanggal;

        public TripItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTempat = itemView.findViewById(R.id.trip_image);
            txtTempat = itemView.findViewById(R.id.trip_tempat);
            txtTanggal = itemView.findViewById(R.id.trip_tanggal);
        }
    }

    public TripAdapter(ArrayList<TripsItem> tripsItems) {
        this.tripsItems = tripsItems;
    }

    @NonNull
    @Override
    public TripItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_item, parent, false);
        TripItemViewHolder viewHolder = new TripItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TripItemViewHolder holder, int position) {
        TripsItem currentItem = tripsItems.get(position);

        holder.imgTempat.setImageResource(currentItem.getImage());
        holder.txtTempat.setText(currentItem.getKota());
        holder.txtTanggal.setText(currentItem.getTanggal_mulai() +" - "+ currentItem.getTanggal_akhir());
    }

    @Override
    public int getItemCount() {
        return tripsItems.size();
    }
}

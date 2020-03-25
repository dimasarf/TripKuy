package com.example.tripkuy.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.DetailTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.DetailTripItem;

import java.util.ArrayList;

public class DetailTripsAdapter extends RecyclerView.Adapter<DetailTripsAdapter.DetailTripsViewHolder> {

    public ArrayList<DetailTripItem> detailTripItems;

    public static class DetailTripsViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgTempat;
        public TextView txtTempat, txtJarak;

        public DetailTripsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTempat = itemView.findViewById(R.id.img_trip);
            txtTempat = itemView.findViewById(R.id.txt_trip_tempat);
            txtJarak = itemView.findViewById(R.id.txt_trip_jarak);
        }
    }

    public DetailTripsAdapter(ArrayList<DetailTripItem> detailTripItems) {
        this.detailTripItems = detailTripItems;
    }

    @NonNull
    @Override
    public DetailTripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_trips_item, parent, false);
        DetailTripsViewHolder detailTripsViewHolder = new DetailTripsViewHolder(v);
        return detailTripsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailTripsViewHolder holder, int position) {
        DetailTripItem currentItem = detailTripItems.get(position);

        holder.imgTempat.setImageResource(currentItem.getImage());
        holder.txtJarak.setText(currentItem.getJarak());
        holder.txtTempat.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return detailTripItems.size();
    }
}

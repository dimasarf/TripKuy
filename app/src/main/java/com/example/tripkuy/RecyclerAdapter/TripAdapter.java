package com.example.tripkuy.RecyclerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.BackgroundWorker.GetDetailRencana;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.RecyclerItems.TripsItem;
import com.example.tripkuy.models.Rencana;
import com.example.tripkuy.models.service.LatLong;
import com.example.tripkuy.models.service.Origin;

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
    public void onBindViewHolder(@NonNull final TripItemViewHolder holder, final int position) {
        final TripsItem currentItem = tripsItems.get(position);
        final Origin origin = new Origin(currentItem.getOrigin(), new LatLong(currentItem.getOriginLat(), currentItem.getOriginLong()));
        holder.imgTempat.setImageResource(currentItem.getImage());
        holder.txtTempat.setText(currentItem.getKota());
        holder.txtTanggal.setText(currentItem.getTanggal_mulai() +" - "+ currentItem.getTanggal_akhir());
        holder.imgTempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("idRencana", "id rencana "+currentItem.getId());
                GetDetailRencana getDetailRencana = new GetDetailRencana(currentItem.getId(),
                        holder.imgTempat.getContext(), currentItem.getDurasi(),
                        currentItem.getTanggal_mulai(), currentItem.getTanggal_akhir(), origin);
                getDetailRencana.execute();
            }
        });
        holder.txtTempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetDetailRencana getDetailRencana = new GetDetailRencana(currentItem.getId(),
                        holder.imgTempat.getContext(), currentItem.getDurasi(),
                        currentItem.getTanggal_mulai(), currentItem.getTanggal_akhir(), origin);
                getDetailRencana.execute();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripsItems.size();
    }
}

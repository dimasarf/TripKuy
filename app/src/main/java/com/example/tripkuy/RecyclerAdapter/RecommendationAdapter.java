package com.example.tripkuy.RecyclerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.RecommendationItem;

import java.util.ArrayList;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecomendationItemViewHolder> {

    private ArrayList<RecommendationItem> recommendationItems;

    private onRecommendationItemClickListener recommendationListener;

    public interface onRecommendationItemClickListener{
        void onItemClick(int position);
        void onSelect(int position);
    }

    public void setRecommendationListener(onRecommendationItemClickListener listener) {
        this.recommendationListener = listener;
    }

    public static class RecomendationItemViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imgTempat;
        public TextView txtTempat, txtJarak;
        public CheckBox checkTempat;

        public RecomendationItemViewHolder(@NonNull View itemView, final onRecommendationItemClickListener listener) {
            super(itemView);
            imgTempat = itemView.findViewById(R.id.img_recommended);
            txtTempat = itemView.findViewById(R.id.txt_recomended_tempat);
            txtJarak = itemView.findViewById(R.id.txt_recomended_jarak);
            checkTempat = itemView.findViewById(R.id.recommended_check);

            checkTempat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        listener.onSelect(position);

                    }
                }
            });
        }
    }

    public RecommendationAdapter(ArrayList<RecommendationItem> items){
        recommendationItems = items;
    }

    @NonNull
    @Override
    public RecomendationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendations_item, parent, false);
        RecomendationItemViewHolder viewHolder = new RecomendationItemViewHolder(v, recommendationListener);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecomendationItemViewHolder holder, int position) {
        RecommendationItem currentItem = recommendationItems.get(position);

        holder.imgTempat.setImageResource(currentItem.getImage());
        holder.txtJarak.setText(currentItem.getJarak());
        holder.txtTempat.setText(currentItem.getName());
        holder.checkTempat.setText(currentItem.getName());

    }

    @Override
    public int getItemCount() {
        return recommendationItems.size();
    }
}

package com.example.tripkuy.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.BackgroundWorker.GetDetailRencana;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.TripsItem;
import com.example.tripkuy.models.service.Article;
import com.example.tripkuy.models.service.LatLong;
import com.example.tripkuy.models.service.Origin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.TripItemViewHolder> {

    private List<Article> articleArrayList;
    Context context;

    public ArticleAdapter(List<Article> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }

    public static class TripItemViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imgArtikel;
        public TextView txtTitle, txtDeskripsi;

        public TripItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArtikel = itemView.findViewById(R.id.img_article);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDeskripsi = itemView.findViewById(R.id.txt_deskripsi);
        }
    }



    @NonNull
    @Override
    public TripItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        TripItemViewHolder viewHolder = new TripItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TripItemViewHolder holder, final int position) {
        final Article currentItem = articleArrayList.get(position);
        Picasso.get().load(currentItem.getUrlToImage()).into( holder.imgArtikel);
        holder.txtTitle.setText(currentItem.getTitle());
        holder.txtDeskripsi.setText(currentItem.getDescription());
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getUrl()));
                context.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }
}

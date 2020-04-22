package com.example.tripkuy.RecyclerAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.BackgroundWorker.UpdateRating;
import com.example.tripkuy.DetailTripActivity;
import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.DetailTripItem;
import com.example.tripkuy.interfaces.GoogleMapAPICallback;
import com.github.vipulasri.timelineview.TimelineView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailTripsAdapter extends RecyclerView.Adapter<DetailTripsAdapter.DetailTripsViewHolder> {

    public ArrayList<DetailTripItem> detailTripItems;
    private Context context;
    public static class DetailTripsViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgTempat;
        public TextView txtTempat, txtJarak, txtDurasi;
        public TimelineView mTimelineView;
        public RatingBar ratingBar;

        public DetailTripsViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            imgTempat = itemView.findViewById(R.id.img_trip);
            txtTempat = itemView.findViewById(R.id.txt_trip_tempat);
            txtJarak = itemView.findViewById(R.id.txt_trip_jarak);
            txtDurasi = itemView.findViewById(R.id.txt_trip_durasi);
            ratingBar = itemView.findViewById(R.id.rating);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);
        }
    }

    public DetailTripsAdapter(ArrayList<DetailTripItem> detailTripItems, Context context) {
        this.detailTripItems = detailTripItems;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailTripsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_trips_item, parent, false);
        DetailTripsViewHolder detailTripsViewHolder = new DetailTripsViewHolder(v, viewType);
        return detailTripsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailTripsViewHolder holder, int position) {
        final DetailTripItem currentItem = detailTripItems.get(position);

        getPhotoReference(detailTripItems.get(position).getName(), new GoogleMapAPICallback() {
            @Override
            public void onPhotoReferenceCallback(String photoReference) {
                Log.d("PHOTOURL", "url "+getPhotoURL(photoReference));
                Picasso.get().load(getPhotoURL(photoReference)).into(holder.imgTempat);
            }
        });
        holder.ratingBar.setRating(currentItem.getRating());
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d("idrute", "id "+currentItem.getId());
                UpdateRating updateRating = new UpdateRating(currentItem.getId(), holder.ratingBar.getRating(), context);
                updateRating.execute();
            }
        });

        holder.txtJarak.setText(currentItem.getJarak());
        holder.txtTempat.setText(currentItem.getName());
        holder.txtDurasi.setText(currentItem.getDurasi());
    }

    public void getPhotoReference(String lokasi, final GoogleMapAPICallback listener){
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/" +
                "json?input=" + lokasi+
                "&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&" +
                "key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        final String[] photoReference = {""};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("candidates");
                    JSONObject candidate = (JSONObject) jsonArray.get(0);
                    JSONArray photos = (JSONArray) candidate.get("photos");
                    JSONObject ref = (JSONObject) photos.get(0);
                    photoReference[0] = ref.getString("photo_reference");
                    Log.d("KONToL", "REF "+ ref.getString("photo_reference"));
                    Log.d("jsonn", "tolol");
                    NewTripActivity.ref = ref.getString("photo_reference");
                    listener.onPhotoReferenceCallback(ref.getString("photo_reference"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MASALAH", error.getMessage());
            }


        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public String getPhotoURL(String referensi){
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&" +
                "photoreference=" +
                referensi +
                "&key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        return url;
    }

    @Override
    public int getItemCount() {
        return detailTripItems.size();
    }
}

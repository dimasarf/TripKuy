package com.example.tripkuy.RecyclerAdapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.AddDestinationDialog;
import com.example.tripkuy.NewTripActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerItems.RecommendationItem;
import com.example.tripkuy.interfaces.GoogleMapAPICallback;
import com.example.tripkuy.models.TempatWisata;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecomendationItemViewHolder> implements
        AddDestinationDialog.AddDestinationDialogListener {

    private ArrayList<RecommendationItem> recommendationItems;

    private onRecommendationItemClickListener recommendationListener;
    private Context context;



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

    public RecommendationAdapter(ArrayList<RecommendationItem> items, Context _context){
        recommendationItems = items;
        context = _context;
    }

    @NonNull
    @Override
    public RecomendationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomendations_item, parent, false);
        RecomendationItemViewHolder viewHolder = new RecomendationItemViewHolder(v, recommendationListener);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecomendationItemViewHolder holder, int position) {
        RecommendationItem currentItem = recommendationItems.get(position);

        if(position == recommendationItems.size() - 1){
            holder.imgTempat.setImageResource(currentItem.getImage());
            holder.txtJarak.setText("");
            holder.txtTempat.setText("");
            holder.checkTempat.setText("");
            holder.txtJarak.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            holder.checkTempat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddDestinationDialog dialog = new AddDestinationDialog();
                    dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "test");

                }
            });
        }
        else {

            getPhotoReference(recommendationItems.get(position).getName(), new GoogleMapAPICallback() {
                @Override
                public void onPhotoReferenceCallback(String photoReference) {
                    Log.d("PHOTOURL", "url "+getPhotoURL(photoReference));
                    Picasso.get().load(getPhotoURL(photoReference)).into(holder.imgTempat);
                }
            });


            holder.txtJarak.setText(round(currentItem.getSimilarity(), 2) * 100+"%");
            holder.txtTempat.setText(currentItem.getName());
            holder.checkTempat.setText(currentItem.getName());
            if(recommendationItems.get(position).isSelected())
                holder.checkTempat.setChecked(true);
        }



    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
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

    @Override
    public int getItemCount() {
        return recommendationItems.size();
    }

    public String getPhotoURL(String referensi){
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&" +
                "photoreference=" +
                referensi +
                "&key=AIzaSyA02Yz09lw6kg_WTc-IqFD2kPP4txoxqVc";
        return url;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public void onDialogListener(TempatWisata tempatWisata) {
        recommendationItems.add(new RecommendationItem(tempatWisata.getId(), 0, tempatWisata.getNama(),0, "", tempatWisata.getLatitude(), tempatWisata.getLongitude()));


    }

    public int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

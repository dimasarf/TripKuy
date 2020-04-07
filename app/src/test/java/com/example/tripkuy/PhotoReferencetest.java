package com.example.tripkuy;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tripkuy.tripssummary.TripSummaryActivity;
import com.example.tripkuy.tripssummary.TripSummaryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhotoReferencetest {
    private RequestQueue mQueue;


    @Test
    public void addition_isCorrect() {
        assertEquals("", getPhotoReference("Tugu Jogja"));
    }

    public String getPhotoReference(String lokasi){
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?" +
                "input=" + lokasi+
                "inputtype=textquery&" +
                "fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:2000@47.6918452,-122.2226413&" +
                "key="+ R.string.google_maps_key;
        final String[] photoReference = {""};
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("candidates");
                    JSONObject candidate = (JSONObject) jsonArray.get(2);
                    JSONObject overview_polyline = (JSONObject) candidate.get("photos");
                    if (overview_polyline != null) {
                        photoReference[0] = overview_polyline.getString("photo_reference");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });
        mQueue.add(request);

        return photoReference[0];
    }
}

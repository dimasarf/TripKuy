package com.example.tripkuy.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripkuy.MainActivity;
import com.example.tripkuy.R;
import com.example.tripkuy.RecyclerAdapter.ArticleAdapter;
import com.example.tripkuy.config.ApiNewsClient;
import com.example.tripkuy.createtrip.CreateTripActivity;
import com.example.tripkuy.interfaces.ApiNewsInterrface;
import com.example.tripkuy.models.service.Article;
import com.example.tripkuy.models.service.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button btn_trip_baru;

    String url;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btn_trip_baru = (Button) root.findViewById(R.id.btn_trip_baru);
        btn_trip_baru.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), CreateTripActivity.class);
                startActivity(intent);
            }
        });
        final RecyclerView mainRecycler = root.findViewById(R.id.news_rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mainRecycler.setLayoutManager(linearLayoutManager);
        final ApiNewsInterrface apiService = ApiNewsClient.getClient().create(ApiNewsInterrface.class);
        Call<NewsResponse> call = apiService.getLatestNews("Pariwisata Yogyakarta","3c4a9bacd78547359b84f195282ff12f");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse>call, Response<NewsResponse> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    if(articleList.size()>0) {
                        final ArticleAdapter mainArticleAdapter = new ArticleAdapter(articleList, getContext());

                        mainRecycler.setAdapter(mainArticleAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<NewsResponse>call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
        return root;
    }
}
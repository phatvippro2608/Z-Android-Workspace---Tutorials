package com.ntp.apiwithretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ntp.apiwithretrofit.adapter.MovieAdapter;
import com.ntp.apiwithretrofit.api.ApiClient;
import com.ntp.apiwithretrofit.api.ApiClient2;
import com.ntp.apiwithretrofit.api.ApiService;
import com.ntp.apiwithretrofit.api.ApiService2;
import com.ntp.apiwithretrofit.databinding.ActivityMainBinding;
import com.ntp.apiwithretrofit.respone.Movie;
import com.ntp.apiwithretrofit.respone.MovieList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    ApiClient apiClient = new ApiClient();
    List<Movie> items = new ArrayList<>();

    private final ApiService apiService = apiClient.getClient().create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        callApi();
        callApi2();
    }

    private void callApi2() {
        ApiClient2 apiClient2 = new ApiClient2();
        apiClient2.getMovieListClient().create(ApiService2.class).getMovieList2("2").enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                binding.prgBarMovie.setVisibility(View.GONE);
                MovieList movieList = response.body();
                items = movieList.getResults();
                MovieAdapter movieAdapter = new MovieAdapter(items);
                binding.rvMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvMovie.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }

    private void callApi() {
        apiService.getMovieList( "1").enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                binding.prgBarMovie.setVisibility(View.GONE);
                MovieList movieList = response.body();
                items = movieList.getResults();
                MovieAdapter movieAdapter = new MovieAdapter(items);
                binding.rvMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvMovie.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
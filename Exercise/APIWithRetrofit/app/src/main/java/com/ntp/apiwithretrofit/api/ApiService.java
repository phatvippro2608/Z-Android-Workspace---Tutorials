package com.ntp.apiwithretrofit.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntp.apiwithretrofit.respone.MovieList;

public interface ApiService {

    //https://api.themoviedb.org/3/movie/popular?api_key=820347940b4e20fdfed3fbde3a5a30fb&language=en-US&page=1
    static final String API_KEY = "820347940b4e20fdfed3fbde3a5a30fb";
    static final String BASEURL = "https://api.themoviedb.org/3/";
    static final String POSTER_BASEURL = "https://image.tmdb.org/t/p/w342";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl(BASEURL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);

    @GET("movie/popular")
    Call<MovieList> getMovieList(@Query("page") String page);
}

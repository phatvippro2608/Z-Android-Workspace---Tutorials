package com.ntp.apiwithretrofit.api;

import com.ntp.apiwithretrofit.respone.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService2 {
    @GET("movie/popular")
    Call<MovieList> getMovieList2(@Query("page") String page);
}

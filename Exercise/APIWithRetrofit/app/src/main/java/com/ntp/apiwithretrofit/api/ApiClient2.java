package com.ntp.apiwithretrofit.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {
    Interceptor interceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            String url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", "820347940b4e20fdfed3fbde3a5a30fb")
                    .build().toString();
            Request request = chain.request().newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    };



    public Retrofit getMovieListClient(){
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(new OkHttpClient().newBuilder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

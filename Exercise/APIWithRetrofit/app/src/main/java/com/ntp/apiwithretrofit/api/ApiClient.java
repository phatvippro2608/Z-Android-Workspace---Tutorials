package com.ntp.apiwithretrofit.api;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private Retrofit retrofit;
    private static final String API_KEY = "820347940b4e20fdfed3fbde3a5a30fb";
    private static final String BASEURL = "https://api.themoviedb.org/3/";
    private Interceptor requestInterceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            String url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()+"";
            Request request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    };

    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

package com.ntp.apiconnectdrive.api;

import static com.ntp.apiconnectdrive.Utils.Constants.TOKEN_BASEURL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.internal.StaticLayoutBuilderConfigurer;
import com.ntp.apiconnectdrive.R;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriveAPIClient {
    private final String GOOGLE_DRIVE_CLIENT_ID = "542569448894-ptki01li9vf0h1udv9s76pgtn21287si.apps.googleusercontent.com";
    private final String GOOGLE_DRIVE_CLIENT_ID3 = "542569448894-ulh6jjrl3hg01msgq1jhapkb8kv3b4n8.apps.googleusercontent.com";
    private final String GOOGLE_DRIVE_CLIENT_SECRET = "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";
    private final String GOOGLE_DRIVE_CLIENT_SECRET3 = "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";
    private final String GOOGLE_DRIVE_REFRESH_TOKEN1 = "1//04YsZmX922oaSCgYIARAAGAQSNwF-L9IrKgKEtsVkMwCgQ16QSKMNQbxd9CZ2lhNbzGJG6y05YKk8NfLvFBtKcVlnq6RmeWwfcdA";
    private final String GOOGLE_DRIVE_REFRESH_TOKEN = "1//04qK46W8ImvSECgYIARAAGAQSNwF-L9Ir19As6AjNE7Xwe48vQPBgkWOHn-hbab5qkm788-XzHj0uiLCjf4RxMMtkHVKhMPHReaM";
    private final String GOOGLE_DRIVE_REFRESH_TOKEN3 = "1//04aVjn01frFGlCgYIARAAGAQSNwF-L9Irb3mkONtdTeDZ7XWZQjtpa4dRDu_N8YaVz6jLs50L83GC9mH87kY2-DpKWFvHWeqX1qA";

    Interceptor interceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            String url = chain.request().url().newBuilder()
                    .addQueryParameter("client_id", GOOGLE_DRIVE_CLIENT_ID)
                    .addQueryParameter("client_secret", GOOGLE_DRIVE_CLIENT_SECRET)
                    .addQueryParameter("refresh_token", GOOGLE_DRIVE_REFRESH_TOKEN)
                    .addQueryParameter("grant_type", "refresh_token")
                    .build().toString();
            Request request = chain.request().newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(request);
        }
    };

    public String getToken(){
        String[] accesstoken = {""};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new RequestBody() {
                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return null;
                        }

                        @Override
                        public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {

                        }
                    };
                    Request request = new Request.Builder()
                            .url("https://oauth2.googleapis.com/token")
                            .post(body)
                            .build();

                    String newURL = request.url().newBuilder()
                            .addQueryParameter("client_id", GOOGLE_DRIVE_CLIENT_ID3)
                            .addQueryParameter("client_secret", GOOGLE_DRIVE_CLIENT_SECRET3)
                            .addQueryParameter("refresh_token", GOOGLE_DRIVE_REFRESH_TOKEN3)
                            .addQueryParameter("grant_type", "refresh_token")
                            .build().toString();

                    request = request.newBuilder().url(newURL).build();


                    try {
                        Response response = client.newCall(request).execute();
                        if(response.isSuccessful()){
                            String responseBody = response.body().string();
                            JSONObject jsonObject = new JSONObject(responseBody);
                            accesstoken[0] = jsonObject.getString("access_token");
                        }else{
                            int errorCode = response.code();
                            String errorMessage = response.message();
                            String errorBody = response.body().string();
                            accesstoken[0] = "Error code: "+errorCode
                                    +"\nError message: \n"+errorMessage
                                    +"\nError body: \n"+errorBody;
                        }
                    } catch (IOException e) {
                        accesstoken[0] = "ERROR "+e;
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        while (accesstoken[0].equals(""));

        return accesstoken[0];
    }
    private OkHttpClient provideOkHttpClientToken(){
        return  new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public Retrofit getRetrofitToken(){
        return new Retrofit.Builder()
                .baseUrl(TOKEN_BASEURL)
                .client(provideOkHttpClientToken())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    Interceptor AuthInterceptor = new Interceptor() {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Headers headers = chain.request().headers().newBuilder()
                    .add("Authorization", getToken())
                    .build();

            Request request = chain.request().newBuilder()
                    .headers(headers)
                    .build();
            return chain.proceed(request);
        }
    };

    public String getFiles(){
        String[] accesstoken = {""};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new RequestBody() {
                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return null;
                        }

                        @Override
                        public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {

                        }
                    };


                    Request request = new Request.Builder()
                            .url("https://www.googleapis.com/drive/v3/files?q=\'1i3GRsvJxqeSxKlUPOlJMm0-EIH2XD5cW\' in parents")
                            .addHeader("Authorization", "Bearer "+getToken())
                            .get()
                            .build();


                    try {
                        Response response = client.newCall(request).execute();
                        if(response.isSuccessful()){
                            String responseBody = response.body().string();
                            accesstoken[0] = responseBody;
                        }else{
                            int errorCode = response.code();
                            String errorMessage = response.message();
                            String errorBody = response.body().string();
                            accesstoken[0] = "Error code: "+errorCode
                                    +"\nError message: \n"+errorMessage
                                    +"\nError body: \n"+errorBody;
                        }
                    } catch (IOException e) {
                        accesstoken[0] = "ERROR "+e;
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        while (accesstoken[0].equals(""));

        return accesstoken[0];
    }

    public String getVideoInfo(){
        String[] accesstoken = {""};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new RequestBody() {
                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return null;
                        }

                        @Override
                        public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {

                        }
                    };



                    Request request = new Request.Builder()
                            .url("https://www.googleapis.com/youtube/v3/videos?id=EUgk1OEDmq4&part=snippet%2CcontentDetails%2Cstatistics")
                            .addHeader("Authorization", "Bearer "+getToken())
                            .addHeader("Accept", "application/json")
                            .get()
                            .build();


                    try {
                        Response response = client.newCall(request).execute();
                        if(response.isSuccessful()){
                            String responseBody = response.body().string();
                            accesstoken[0] = responseBody;
                        }else{
                            int errorCode = response.code();
                            String errorMessage = response.message();
                            String errorBody = response.body().string();
                            accesstoken[0] = "Error code: "+errorCode
                                    +"\nError message: \n"+errorMessage
                                    +"\nError body: \n"+errorBody;
                        }
                    } catch (IOException e) {
                        accesstoken[0] = "ERROR "+e;
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        while (accesstoken[0].equals(""));

        return accesstoken[0];
    }
}

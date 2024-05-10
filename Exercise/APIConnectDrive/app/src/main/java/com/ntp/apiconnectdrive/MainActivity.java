package com.ntp.apiconnectdrive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntp.apiconnectdrive.api.DriveAPIClient;
import com.ntp.apiconnectdrive.api.DriveAPIService;
import com.ntp.apiconnectdrive.api.YouTubeExtractor;
import com.ntp.apiconnectdrive.api.YoutubeAPI;
import com.ntp.apiconnectdrive.databinding.ActivityMainBinding;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding binding;
    Response response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnGetAccessToken.setOnClickListener(view->{


//            Thread thread = new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        OkHttpClient client = new OkHttpClient();
//                        RequestBody body = RequestBody.create(null, new byte[0]);
//                        Request request = new Request.Builder()
//                                .url("https://oauth2.googleapis.com/token?client_id=542569448894-ptki01li9vf0h1udv9s76pgtn21287si.apps.googleusercontent.com&client_secret=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&refresh_token=1//04qK46W8ImvSECgYIARAAGAQSNwF-L9Ir19As6AjNE7Xwe48vQPBgkWOHn-hbab5qkm788-XzHj0uiLCjf4RxMMtkHVKhMPHReaM&grant_type=refresh_token")
//                                .post(new RequestBody() {
//                                    @Nullable
//                                    @Override
//                                    public MediaType contentType() {
//                                        return null;
//                                    }
//
//                                    @Override
//                                    public void writeTo(@NonNull BufferedSink bufferedSink) throws IOException {
//
//                                    }
//                                })
//                                .build();
//
//                        try {
//                            response = client.newCall(request).execute();
//                            String responseBody = response.body().string();
//                            JSONObject jsonObject = new JSONObject(responseBody);
//
//                            binding.tvRespone.setText(jsonObject.getString("access_token"));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            thread.start();
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    getToken();
//                }
//            });
//            thread.start();

            DriveAPIClient client = new DriveAPIClient();
            binding.tvRespone.setText(client.getToken());

        });

        binding.btnPostImage.setOnClickListener(view -> {
            DriveAPIClient client = new DriveAPIClient();
            binding.tvRespone.setText(client.getFiles());
        });

        binding.btnGetVideoInfo.setOnClickListener(view -> {
            DriveAPIClient client = new DriveAPIClient();
            binding.tvRespone.setText(client.getVideoInfo());
        });

        final boolean[] isSuccess = {true};
        binding.btnRootVideo.setOnClickListener(view -> {
            new Thread(() -> {
                try {
                    List<YouTubeExtractor.YouTubeVideo> videoList = YouTubeExtractor.extractVideos("https://www.youtube.com/watch?v=Huu3H5ei_-8");
                    for (YouTubeExtractor.YouTubeVideo video : videoList) {
                        Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
                        Log.d("Video Info", "Video ID: " + video.getVideoId());
                        Log.d("Video Info", "URL: " + video.getUrl());
                        Log.d("Video Info", "Quality: " + video.getQuality());
                        Log.d("Video Info", "Mime Type: " + video.getMimeType());
                        Log.d("Video Info", "----------------------");
                    }
                    isSuccess[0] = false;
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }).start();

//            String youtubeUrl = "https://www.youtube.com/watch?v=Huu3H5ei_-8";
//
//            YouTubeExtractor.extract(youtubeUrl, new YouTubeExtractor.OnExtractionComplete() {
//                @Override
//                public void onExtractionComplete(List<YouTubeExtractor.YouTubeVideo> videoList) {
//                    // Xử lý danh sách video ở đây
//                    for (YouTubeExtractor.YouTubeVideo video : videoList) {
//                        Log.d("Video Info", "Video ID: " + video.getVideoId());
//                        Log.d("Video Info", "URL: " + video.getUrl());
//                        Log.d("Video Info", "Quality: " + video.getQuality());
//                        Log.d("Video Info", "Mime Type: " + video.getMimeType());
//                        Log.d("Video Info", "----------------------");
//                    }
//                }
//            });
        });
    }

}
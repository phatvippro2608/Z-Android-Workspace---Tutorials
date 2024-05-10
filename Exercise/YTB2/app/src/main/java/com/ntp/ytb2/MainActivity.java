package com.ntp.ytb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.ntp.ytb2.databinding.ActivityMainBinding;
import com.yausername.youtubedl_android.BuildConfig;
import com.yausername.youtubedl_android.YoutubeDL;
import com.yausername.youtubedl_android.YoutubeDLException;
import com.yausername.youtubedl_android.YoutubeDLRequest;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "StreamingExample";
    ActivityMainBinding binding;
    private String processId = "MyDlProcess";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            YoutubeDL.getInstance().init(this);
        } catch (YoutubeDLException e) {
            Log.e("YOUTUBE", "failed to initialize youtubedl-android", e);
        }

        binding.abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/watch?v=Pv61yEcOqpw";
                YoutubeDLRequest request = new YoutubeDLRequest(url);
                request.addOption("-f", "bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best");




                Disposable disposable = Observable.fromCallable(()->
                                YoutubeDL.getInstance().execute(request)
                        )
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(streamInfo -> {
                            Toast.makeText(MainActivity.this, "failed to get stream url", Toast.LENGTH_LONG).show();

//                            String videoUrl = streamInfo.getUrl();
//                            if (TextUtils.isEmpty(videoUrl)) {
//                                Toast.makeText(MainActivity.this, "failed to get stream url", Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, videoUrl, Toast.LENGTH_LONG).show();
//                                binding.abc.setText(videoUrl.substring(0, 10));
//                            }
                            streamInfo.getOut();
                        }, e -> {
                            if(BuildConfig.DEBUG) Log.e(TAG,  "failed to get stream info", e);
                            Toast.makeText(MainActivity.this, "streaming failed. failed to get stream info", Toast.LENGTH_LONG).show();
                        });
                compositeDisposable.add(disposable);
            }
        });








//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // Gửi một thông điệp đến luồng UI chính
//                try {
//                    YoutubeDLRequest request = new YoutubeDLRequest("https://www.youtube.com/watch?v=vN0optTElms");
//                    request.addOption("-f", "best");
//                    VideoInfo streamInfo = null;
//                    streamInfo = YoutubeDL.getInstance().getInfo(request);
//                    VideoInfo finalStreamInfo = streamInfo;
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.abc.setText(finalStreamInfo.getUrl());
//                        }
//                    });
//                } catch (YoutubeDLException e) {
//                    throw new RuntimeException(e);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                } catch (YoutubeDL.CanceledException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        });
//        thread.start();
//        Toast.makeText(this, "na", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
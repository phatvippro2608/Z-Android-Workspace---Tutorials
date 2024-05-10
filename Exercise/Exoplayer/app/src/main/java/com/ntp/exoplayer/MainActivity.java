package com.ntp.exoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ntp.exoplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        while (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 200);
        }
        binding.btnVideo1.setOnClickListener(view -> {
//            String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
//            String url = "https://drive.google.com/uc?id=1zZLIC7PKP9gV3xTkEvpIGU2awwWiENQW";
            String url = "https://rr1---sn-8qj-nbo6y.googlevideo.com/videoplayback?expire=1711662948&ei=BJMFZoSiMceEs8IPvY2dqAw&ip=103.200.23.98&id=o-AM444yKpu_k1RdZgZjCLZQsrNOyaJ7oEOL8ZYLGdgNNi&itag=22&source=youtube&requiressl=yes&xpc=EgVo2aDSNQ%3D%3D&mh=0L&mm=31%2C26&mn=sn-8qj-nbo6y%2Csn-30a7yner&ms=au%2Conr&mv=u&mvi=1&pl=24&spc=UWF9fxYsAZ6_5fhYekX7Gt_6QefS7-4HqPwKIjFAmixYuZQ&vprv=1&svpuc=1&mime=video%2Fmp4&ns=FsIG-as2Yoamuhrux6NKxn8Q&cnr=14&ratebypass=yes&dur=1186.121&lmt=1711630299936853&mt=1711639677&fvip=3&fexp=51141541&c=WEB&sefc=1&txp=6308224&n=-ZeYMXfvULLzmaT&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cxpc%2Cspc%2Cvprv%2Csvpuc%2Cmime%2Cns%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AJfQdSswRAIgERhvaCcsk9d48dkoC-lOdHDadkSuNON3EgT7XuFtEX0CIGpZ-OJRGsig6WtDqI2PsyAOrBzgJCw11IqoiXevu8e0&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl&lsig=ALClDIEwRgIhAN3rvoiYjfaBpcfEcosBaCwMU2Z61Fpg4VgoBMv0X_PiAiEAk2ifbgp_7E1FZFqXdu_wofxH25UrmNxlSAvUlmWiE4M%3D";
            gotoPlayerPaget(url);
        });

        binding.btnVideo2.setOnClickListener(view -> {
            String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4";
            gotoPlayerPaget(url);
        });

        binding.btnVideo3.setOnClickListener(view -> {
            String url = "https://github.com/rafaelreis-hotmart/Audio-Sample-files/raw/master/sample.mp3";
            gotoPlayerPaget(url);
        });

    }
    private void gotoPlayerPaget(String url){
        Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
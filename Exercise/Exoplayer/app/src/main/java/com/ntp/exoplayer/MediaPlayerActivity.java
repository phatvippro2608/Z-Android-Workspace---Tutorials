package com.ntp.exoplayer;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.ui.DefaultTimeBar;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.TimeBar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ntp.exoplayer.databinding.ActivityMediaPlayerBinding;
import com.ntp.exoplayer.databinding.CustomMediaControllerBinding;

import java.util.List;

public class MediaPlayerActivity extends AppCompatActivity {


    ActivityMediaPlayerBinding binding;
    ExoPlayer player;
    Handler handler;
    Runnable runnable;
    String name;
    boolean check = true, isFullscreen = false;

    Context context;
    @OptIn(markerClass = UnstableApi.class) protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMediaPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = this;
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        player = new ExoPlayer.Builder(this)
                .setSeekForwardIncrementMs(10000)
                .setSeekBackIncrementMs(10000)
                .build();
        binding.playerView.setPlayer(player);

        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);


        //Set currenttime
        name = URLUtil.guessFileName(url, null, null);
        TextView txtName = findViewById(R.id.txtName);
        txtName.setText(name);
        SharedPreferences pre = this.getPreferences(Context.MODE_PRIVATE);
        long currentPlay = pre.getLong("time_"+name, 0);
        player.seekTo(currentPlay);

        player.prepare();
        player.play();



        ImageView imgPlayPause = findViewById(R.id.exo_play_pause_img);
        ImageView imgRew = findViewById(R.id.exo_rew);
        ImageView imgFfwd = findViewById(R.id.exo_ffwd);


        imgPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                    imgPlayPause.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.play_arrow_icon));
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }else if(player.getPlaybackState() == Player.STATE_READY){
                    player.play();
                    imgPlayPause.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pause_icon));
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }else if(player.getPlaybackState() == Player.STATE_ENDED){
                    player.seekToDefaultPosition();
                    player.setPlayWhenReady(true);
                    imgPlayPause.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.pause_icon));
                    imgRew.setVisibility(View.VISIBLE);
                    imgFfwd.setVisibility(View.VISIBLE);
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }
            }
        });

        player.addListener(new Player.Listener() {
            @Override
            public void onEvents(Player player, Player.Events events) {
                if(player.isPlaying()){
                    Glide.with(getApplicationContext())
                            .load(R.drawable.pause_icon)
                            .into(imgPlayPause);
                    imgRew.setVisibility(View.VISIBLE);
                    imgFfwd.setVisibility(View.VISIBLE);
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }else if(player.getPlaybackState() == Player.STATE_READY){
                    Glide.with(getApplicationContext())
                            .load(R.drawable.play_arrow_icon)
                            .into(imgPlayPause);
                    imgRew.setVisibility(View.VISIBLE);
                    imgFfwd.setVisibility(View.VISIBLE);
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }else if(player.getPlaybackState() == Player.STATE_ENDED){
                    Glide.with(getApplicationContext())
                            .load(R.drawable.reload_icon)
                            .into(imgPlayPause);
                    imgRew.setVisibility(View.INVISIBLE);
                    imgFfwd.setVisibility(View.INVISIBLE);
                    saveCurrentTimeVideo(player.getCurrentPosition());
                }


                if (player.getPlaybackState() == Player.STATE_READY && check || player.getPlaybackState() == Player.STATE_ENDED && check) {
                    check = false;
                    long duration = player.getDuration();
                    binding.timebarCustom.setDuration(duration);
                    binding.timebarCustom2.setDuration(duration);
                }

                long bufferLocation = player.getBufferedPosition();
                binding.timebarCustom.setBufferedPosition(bufferLocation);
                binding.timebarCustom2.setBufferedPosition(bufferLocation);
            }
        });

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable(){
            @Override
            public void run() {
                long newLocation = player.getCurrentPosition();
                binding.timebarCustom.setPosition(newLocation);
                binding.timebarCustom2.setPosition(newLocation);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

        binding.playerView.setControllerVisibilityListener(new PlayerView.ControllerVisibilityListener() {
            @Override
            public void onVisibilityChanged(int visibility) {
                if(visibility == View.VISIBLE && !isFullscreen){
                    Toast.makeText(MediaPlayerActivity.this, "Controller", Toast.LENGTH_SHORT).show();
                    binding.timebarCustom.setVisibility(View.VISIBLE);
                    binding.timebarCustom2.setVisibility(View.GONE);
                }

                if(visibility == View.GONE && !isFullscreen){
                    Toast.makeText(context, "Hide Controller", Toast.LENGTH_SHORT).show();
                    binding.timebarCustom.setVisibility(View.GONE);
                    binding.timebarCustom2.setVisibility(View.VISIBLE);
                }
            }
        });

        //Timebar
        binding.timebarCustom.addListener(new TimeBar.OnScrubListener() {

            @Override
            public void onScrubStart(TimeBar timeBar, long position) {
                player.seekTo(position);
                timeBar.setPosition(position);
            }
            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                player.seekTo(position);
                timeBar.setPosition(position);
            }
            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                if(!canceled){
                    player.seekTo(position);
                    timeBar.setPosition(position);
                }
            }
        });

        binding.timebarCustom2.addListener(new TimeBar.OnScrubListener() {
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {
                player.seekTo(position);
                timeBar.setPosition(position);
            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                player.seekTo(position);
                timeBar.setPosition(position);
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                if(!canceled){
                    player.seekTo(position);
                    timeBar.setPosition(position);
                }
            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(runnable);
            }
        });

        ImageView imgFullscreen = findViewById(R.id.exo_fullscreen_img);
        DefaultTimeBar exo_progress = findViewById(R.id.exo_progress);
        imgFullscreen.setOnClickListener(view->{
            if(!isFullscreen){
                imgFullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fullscreen_exit_icon));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                binding.timebarCustom.setVisibility(View.GONE);
                binding.timebarCustom2.setVisibility(View.GONE);
                exo_progress.setVisibility(View.VISIBLE);
            }else{
                imgFullscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fullscreen_icon));
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                binding.timebarCustom.setVisibility(View.VISIBLE);
                binding.timebarCustom2.setVisibility(View.VISIBLE);
                exo_progress.setVisibility(View.GONE);
            }
            isFullscreen = !isFullscreen;
        });
    }

//    private void setHeight() {
//        binding.playerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                binding.playerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                int playerViewHeight = binding.playerView.getHeight();
//                findViewById(R.id.exo_progress).getHeight();
//
//                ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
//                constraintLayout.setMinHeight(playerViewHeight);
//                Toast.makeText(context, String.valueOf(playerViewHeight), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    protected void onPause() {
        super.onPause();
        saveCurrentTimeVideo(player.getCurrentPosition());
        player.stop();
        handler.removeCallbacks(runnable);
    }

    private void saveCurrentTimeVideo(long time){
        SharedPreferences pre = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putLong("time_"+name, time);
        editor.apply();
    }
}
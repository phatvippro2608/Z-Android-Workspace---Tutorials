package com.ntp.recordaudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.ntp.recordaudio.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CODE = 200;
    private static final int REQUEST_PERMISSION_STORAGE_CODE = 201;
    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String audioFilePath;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.btnRecord.setOnClickListener(view->{
//
//            if(!isRecording){
//
////                audioFilePath = getExternalCacheDir().getAbsolutePath()+String.format("/audio_%d.3gp", System.currentTimeMillis());
//                audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+String.format("/audio_%d.3gp", System.currentTimeMillis());
//                requestPermissions();
//                binding.btnRecord.setText("STOP");
//            }else{
//                stopRecording();
//                binding.btnRecord.setText("RECORD");
//                binding.audioNametxt.setText(audioFilePath);
//            }
//        });



        binding.btnRecord.setOnClickListener(view -> {
            if(isMicrophonePresent()){
                getMicrophonePermission();
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
                    if(!isRecording){
                        audioFilePath = getRecordingFilePath();
                        startRecording();
                        binding.btnRecord.setText("STOP");
                        binding.audioNametxt.setText("Audio Name");
                        binding.btnPlay.setVisibility(View.INVISIBLE);
                    }else{
                        stopRecording();
                        binding.btnRecord.setText("RECORD");
                        binding.audioNametxt.setText(audioFilePath);
                        binding.btnPlay.setVisibility(View.VISIBLE);
                    }
                }
            }else{
                Toast.makeText(this, "Thiết bị của bạn không hỗ trợ MICROPHONE", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnPlay.setOnClickListener(view -> {
            try {
                ArrayList<String> filepaths = new ArrayList<>();
                filepaths = getMusicFiles();
                mediaPlayer = new MediaPlayer();
//                mediaPlayer.setDataSource(audioFilePath);
                mediaPlayer.setDataSource(filepaths.get(0));
                mediaPlayer.prepare();
                mediaPlayer.start();
//                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.cheri_cheri_lady);
//                mediaPlayer.start();
                Toast.makeText(this, "Playing audio", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    private boolean isMicrophonePresent(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }else{
            return false;
        }
    }


    private void getMicrophonePermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECORD_AUDIO
            }, REQUEST_PERMISSION_CODE);
        }
    }

    private void startRecording() {
        if(mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
        }

        try{
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


    private void stopRecording() {
        if(isRecording && mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDic = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDic, String.format("/audio_%d.3gp", System.currentTimeMillis()));
        return file.getPath();
    }

    private ArrayList<String> getMusicFiles(){
        ArrayList<String> musicFilesList = new ArrayList<>();
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDir  = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        if(musicDir !=  null){
            File[] files = musicDir.listFiles();

            if(files!=null){
                for (File file:files){
                    String filepath = file.getAbsolutePath();
                    if(filepath.endsWith(".3gp") || filepath.endsWith(".mp3")){
                        musicFilesList.add(filepath);
                    }
                }
            }
        }

        return  musicFilesList;
    }







//    private void startRecording() {
//        if(mediaRecorder==null){
//            mediaRecorder = new MediaRecorder();
//        }
//
//        try{
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            mediaRecorder.setOutputFile(audioFilePath);
//
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//            isRecording = true;
//            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }
//    }



    private void requestPermissions(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && Build.VERSION.SDK_INT<=Build.VERSION_CODES.Q){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
            }
            startRecording();
        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
            }

            if(!Environment.isExternalStorageManager()){
                try{
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:$s", getApplicationContext().getPackageName())));
                    startActivityIfNeeded(intent, REQUEST_PERMISSION_STORAGE_CODE);
                }catch(Exception ex){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivityIfNeeded(intent, REQUEST_PERMISSION_STORAGE_CODE);
                }
            }

            startRecording();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Được cấp quyền", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isRecording){
            stopRecording();
        }
    }
}
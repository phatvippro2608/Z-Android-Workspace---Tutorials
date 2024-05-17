package com.tsnguyentanphat.font;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView txtText;
    ListView lvFonts;
    ArrayAdapter<String>fontAdapter;
    String[] arrFonts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fontName = fontAdapter.getItem(position);
                Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/"+fontName);
                txtText.setTypeface(typeface);
                xulyNhac();
            }
        });
    }

    private void xulyNhac() {
        try {
            AssetFileDescriptor afd = getAssets().openFd("musics/(BAE) TĂNG DUY TÂN - DẠ VŨ - Official Music Video(1).mp3");
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addControls() {
        txtText = (TextView) findViewById(R.id.txtText);
        lvFonts = (ListView) findViewById(R.id.lvFonts);
        fontAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvFonts.setAdapter(fontAdapter);
        try {
            AssetManager assetManager = getAssets();
            arrFonts = assetManager.list("fonts");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fontAdapter.addAll(arrFonts);
    }
}
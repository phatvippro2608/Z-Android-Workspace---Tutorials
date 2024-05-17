package com.tsnguyentanphat.example_2_localfileasset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            JSONArray array = root.getJSONArray("danhsach");
//            JSONArray array = new JSONArray(json);
            for(int i = 0; i<array.length(); i++){
                JSONObject khoahoc = array.getJSONObject(i);
                Log.i("Result",khoahoc.getString("khoahoc")+khoahoc.getString("ngayhoc")+khoahoc.getString("phonghoc"));
            }
        } catch (Exception e) {
            Log.e("TAG", "load json error"+e);
        }
    }
}
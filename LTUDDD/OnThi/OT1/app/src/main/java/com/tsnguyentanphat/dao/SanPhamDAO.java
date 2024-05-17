package com.tsnguyentanphat.dao;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.tsnguyentanphat.activity.MainActivity;
import com.tsnguyentanphat.activity.R;
import com.tsnguyentanphat.model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SanPhamDAO extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public ArrayList getSanPham(){
        ArrayList<SanPham>sanPhams=new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("image.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer,StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            JSONArray arrSanPham = root.getJSONArray("sanpham");
            for(int i = 0; i<arrSanPham.length(); i++){
                JSONObject sp = arrSanPham.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setD(getDrawFromAsset(sp.getString("id")));
                sanPham.setName(sp.getString("name"));
                sanPham.setCost(sp.getString("cost"));
                sanPhams.add(sanPham);
            }
        } catch (Exception e) {
            Log.e("ERROR","JSON:"+e);
        }
        return sanPhams;
    }
    private Drawable getDrawFromAsset(String s) {
        Drawable d;
        try {
            InputStream inputStream = getAssets().open("image/"+s);
            d = Drawable.createFromStream(inputStream,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return d;
    }
}

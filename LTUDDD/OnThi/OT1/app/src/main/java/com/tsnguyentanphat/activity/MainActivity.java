package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.tsnguyentanphat.adapter.SanPhamAdapter;
import com.tsnguyentanphat.dao.SanPhamDAO;
import com.tsnguyentanphat.model.SanPham;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imgHinh1;
    ListView lvSanPham;
    String[] arrHinh;
    String url;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        imgHinh1 = findViewById(R.id.imgHinh1);
        lvSanPham = findViewById(R.id.lvSanPham);
        sanPhamAdapter = new SanPhamAdapter(
                MainActivity.this,
                R.layout.item_sanpham
        );
        lvSanPham.setAdapter(sanPhamAdapter);
        Drawable t = getDrawFromAsset("h1.jpg");
        SanPham sanPham = new SanPham(t,"Hoa1","15000");
        sanPhamAdapter.add(sanPham);
        ArrayList<SanPham>sanPhams= getSanPham();
        sanPhamAdapter.addAll(sanPhams);
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
    public ArrayList getSanPham(){
        ArrayList<SanPham>sanPhams=new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("image.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
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
}
package com.ntp.requestpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import com.ntp.requestpermission.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private static final int POST_NOTIFICATIONS_CODE = 1;
    private static final int READ_STORAGE_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txtXinQuyen.setOnClickListener(view->{
            checkPermissionNew();
        });
    }

    private void checkPermissionNew(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q){
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if(!Environment.isExternalStorageManager()){
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION );
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:$s", getApplicationContext().getPackageName())));
                    startActivityIfNeeded(intent, 101);
                }catch (Exception ex){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivityIfNeeded(intent, 101);
                }
            }
        }
    }
    private void checkStoragePermission(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, READ_STORAGE_CODE);
        }else{
            Toast.makeText(this, "Đã cấp quyền xử lý bộ nhớ trong", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkNotifyPermission(){
        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS}, POST_NOTIFICATIONS_CODE);
        }else{
            Toast.makeText(this, "Đã cấp quyền xử thông báo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == POST_NOTIFICATIONS_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Bạn đã cấp quyền", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Quyền thông báo bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == READ_STORAGE_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Bạn đã cấp quyền", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Quyền bộ nhớ bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
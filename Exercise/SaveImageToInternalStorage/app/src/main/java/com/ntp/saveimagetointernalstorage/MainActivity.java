package com.ntp.saveimagetointernalstorage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ntp.saveimagetointernalstorage.adapter.ImageAdapter;
import com.ntp.saveimagetointernalstorage.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.ntp.saveimagetointernalstorage.domain.Picture;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    ActivityResultLauncher<Intent> resultLauncher;
    Bitmap bitmap;
    BitmapDrawable bitmapDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        String pathImage = "/storage/emulated/0/Download/1709818062425.jpg";
        String pathImage = "/data/user/0/com.ntp.saveimagetointernalstorage/app_images/1709818718792.jpg";
        Glide.with(this)
                .load("file://"+pathImage)
                .into(binding.imgReview);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("1", "notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        registerResult();
        binding.btnUpload.setOnClickListener(view -> pickImage());
        binding.btnSave.setOnClickListener(view->saveImage());
        binding.btnShowNotify.setOnClickListener(view->showNotification());

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<Picture> items = new ArrayList<>();

        items.add(new Picture(0, "/data/user/0/com.ntp.saveimagetointernalstorage/app_images/1709818718792.jpg"));
        items.add(new Picture(0, "/data/user/0/com.ntp.saveimagetointernalstorage/app_images/1709818718792.jpg"));
        items.add(new Picture(0, "/storage/emulated/0/Download/1709818062425.jpg"));
        items.add(new Picture(0, "/data/user/0/com.ntp.saveimagetointernalstorage/app_images/1709818718792.jpg"));
        ImageAdapter imageAdapter = new ImageAdapter(items);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(imageAdapter);
    }

    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult rs) {
                        try{
                            Uri imageUri = rs.getData().getData();
                            binding.imgReview.setImageURI(imageUri);
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


    private void saveImage() {
        bitmapDrawable = (BitmapDrawable) binding.imgReview.getDrawable();
        bitmap = bitmapDrawable.getBitmap();
        String path = save(bitmap);
//        String path = save(bitmap);
        binding.path.setText(path);

    }


    private String saveImageToInternalStorage(Bitmap image) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir("images2", Context.MODE_PRIVATE);
        String filename = String.format("%d.jpg", System.currentTimeMillis());
        File imagePath = new File(directory, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imagePath);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imagePath.getAbsolutePath();
    }

    private String save(Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        //Sử dục enim thay cho string music
        File directory = new File(sdCard.getAbsolutePath()+"/"+Environment.DIRECTORY_MUSIC);
        directory.mkdir();
        String filename = String.format("%d.jpg", System.currentTimeMillis());
        File outfile = new File(directory, filename);

        Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();

        try {
            fileOutputStream = new FileOutputStream(outfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outfile.getAbsolutePath();
//            fileOutputStream.flush();
//            fileOutputStream.close();
//
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    public void showNotification(){
        final String CHANNEL_ID = "1";

        Bitmap bitmapLogo = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notify_icon)
                .setContentTitle("Lưu hình ảnh thành công")
                .setContentText("Hình ảnh đã được lưu vào bộ nhớ trong")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle("Xử lý hình ảnh")
                        .bigText("Hey guy, in this lession I will show you how to create the custom notification. This video is helpfull, Please push subprise and bell icon button")
                )
                .setLargeIcon(bitmapLogo);


        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
    }
}
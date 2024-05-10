package com.ntp.worldskill2022.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.createChooser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ntp.worldskill2022.MainActivity;
import com.ntp.worldskill2022.R;
import com.ntp.worldskill2022.database.Database;
import com.ntp.worldskill2022.databinding.FragmentTicketCreateBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.SimpleFormatter;

public class TicketCreateFragment extends Fragment {

    FragmentTicketCreateBinding binding;
    public static final int GALLERY_CODE = 665;

    private Uri imageFilePath;
    private Bitmap imageToStore;
    
    private Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketCreateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inflate the layout for this fragment
        setStarts();
        setEvents();
        return view;
    }

    private void setEvents() {

        binding.btnChoosePicTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent gallery = new Intent();
//                gallery.setType(("image/*"));
//                gallery.setAction(Intent.ACTION_GET_CONTENT);
//                getActivity().startActivityForResult(createChooser(gallery, "Select image"));


//                if(ActivityCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
//                {
//                    requestPermissions(
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            2000);
//                }
//                else {
//                    startGallery();
//                }

                startGallery();
            }
        });

        binding.btnCreateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.DBVersion);
                String ticketType = binding.spnTicketType.getText().toString();
                String ticketAudName = binding.edtNameTicket.getText().toString();
                String ticketSeat = "";
                String ticketSeatString1 = "";
                int ticketSeatString2 = ThreadLocalRandom.current().nextInt(1,11);
                int randInt = ThreadLocalRandom.current().nextInt(1,4);
                switch (randInt){
                    case 1: ticketSeatString1 = "A"; break;
                    case 2: ticketSeatString1 = "B"; break;
                    case 3: ticketSeatString1 = "C"; break;
                }
                ticketSeat = ticketSeatString1+ticketSeatString2 +" Row"+ticketSeatString2+" Column"+ticketSeatString2;
                //TICKET DATE
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MainActivity.PATTERN_DATE);
                String tickeTime = simpleDateFormat.format(date);
//                int  day = now.getDate();
//                int month = now.getMonth();
//                int year = now.getYear();
//                time.setTime(now);

                Toast.makeText(getActivity(), tickeTime+"", Toast.LENGTH_SHORT).show();
                boolean result = database.insertTicket(ticketType, ticketAudName, ticketSeat, imageToStore, tickeTime);
                if(result){
                    Toast.makeText(getActivity(), "Add Success!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Add Fail!", Toast.LENGTH_SHORT).show();
                }
                getParentFragmentManager().beginTransaction().replace(R.id.container, MainActivity.ticketsFragment).commit();
            }

        });
    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(cameraIntent, GALLERY_CODE);
    }

    private void setStarts() {
        String[] ticketType = new String[] {"Closing Ceremony", "Opening Ceremony"};
        ArrayAdapter<String> arrayAdapterTicketType = new ArrayAdapter<>(getActivity(), R.layout.custom_dropdownmenu, ticketType);
        binding.spnTicketType.setAdapter(arrayAdapterTicketType);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == TicketCreateFragment.GALLERY_CODE && resultCode == RESULT_OK &&  data.getData() != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageFilePath);
                binding.imgTicket.setImageBitmap(imageToStore);
                Toast.makeText(getActivity(), resultCode, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Khong thanh cong", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Khong thanh cong", Toast.LENGTH_SHORT).show();
        }

    }

}
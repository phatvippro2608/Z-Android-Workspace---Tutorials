package com.ntp.worldskill2022.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ntp.worldskill2022.MainActivity;
import com.ntp.worldskill2022.R;
import com.ntp.worldskill2022.databinding.FragmentTicketCreateBinding;
import com.ntp.worldskill2022.databinding.FragmentTicketDetailsBinding;
import com.ntp.worldskill2022.model.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class TicketDetailsFragment extends Fragment {
    private static FragmentTicketDetailsBinding binding;
//    private FragmentTicketDetailsBinding binding;
    Ticket[] tk = {new Ticket()};
    public static Ticket tk2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Inflate the layout for this fragment
        getDataTicket();
        tk[0] = tk2;
        binding.imgTicket.setImageBitmap(tk[0].getTicketPic());
        binding.txtTicketType.setText("Ticket type: "+tk[0].getTicketType());
        binding.txtTicketAudName.setText("Audience's name: "+tk[0].getTicketAudienceName());
        binding.txtTicketSeat.setText("Seat: "+tk[0].getTicketSeat());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MainActivity.PATTERN_DATE);
        Date ticketDate;
        try {
             ticketDate = simpleDateFormat.parse(tk[0].getTicketTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        binding.txtTicketTime.setText("Time: "+simpleDateFormat.format(ticketDate));
        binding.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewScreenShot = binding.layoutScreenshot;
                viewScreenShot.setDrawingCacheEnabled(true);
                Bitmap bm = getBitmapFromView(viewScreenShot);
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
//                binding.imgDemo.setImageDrawable(bitmapDrawable);
                saveBitmapToStorage(getActivity(), bm);
                
            }
        });
        return view;

    }
    private Bitmap getBitmapFromView(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
    private void getDataTicket(){
        getParentFragmentManager().setFragmentResultListener(TicketsFragment.TICKET_KEY, getActivity(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if(requestKey==TicketsFragment.TICKET_KEY){
//                    tk[0] = (Ticket) result.getSerializable("ticket");

//                    binding.txtTicketTime.setText(tk[0].getTicketTime().toString());
//                    FragmentManager fragmentManager = getParentFragmentManager();
//                    fragmentManager.clearFragmentResultListener(TicketsFragment.TICKET_KEY);
                }
            }
        });
    }
    public void saveStorage(Bitmap bm){
        // Assume block needs to be inside a Try/Catch block.
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        Integer counter = 0;
        File file = new File(path, "Ticket"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
        try {
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),file.getAbsolutePath(),file.getName(), file.getName());
            Toast.makeText(getActivity(), "Thanh Cong", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Uri saveBitmapToStorage(Context context, Bitmap bitmap) {
        try {
            Date now = new Date();
            String timeStamp = String.valueOf(now.getTime());

            // Get the directory for the user's public pictures directory.
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String imageFileName = "Image_" + timeStamp + ".jpg";

            File image = new File(dir, imageFileName);

            OutputStream fos = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.MediaColumns.DATA, image.getAbsolutePath());

            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
            binding.imgDemo.setImageDrawable(bitmapDrawable);
            return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } catch (Exception e) {
            Log.e("ImageUtils", "Error saving image: " + e.getMessage());
            return null;
        }
    }
}
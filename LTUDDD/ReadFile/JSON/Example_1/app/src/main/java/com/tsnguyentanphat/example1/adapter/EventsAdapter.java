package com.tsnguyentanphat.example1.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.tsnguyentanphat.example1.R;
import com.tsnguyentanphat.example1.model.Events;

public class EventsAdapter extends ArrayAdapter<Events> {
    Activity context;
    int resource;
    public EventsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
         this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource,null);

        ImageView imgPicture = customView.findViewById(R.id.imgPicture);
        TextView txtTitle = customView.findViewById(R.id.txtTitle);
        TextView txtIntroduction = customView.findViewById(R.id.txtIntroduction);
        TextView txtStatus = customView.findViewById(R.id.txtStatus);
        LinearLayout layoutList = customView.findViewById(R.id.layoutList);


        Events events = getItem(position);
        Glide.with(getContext()).load("https://upload.wikimedia.org/wikipedia/commons/8/87/LH_95.jpg").into(imgPicture);
        //imgPicture.setImageResource(R.drawable.picture);
        txtTitle.setText(events.getTitle());
        txtIntroduction.setText(events.getIntroduction());
        txtStatus.setText(events.getStatus());
        layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return customView;
    }
}

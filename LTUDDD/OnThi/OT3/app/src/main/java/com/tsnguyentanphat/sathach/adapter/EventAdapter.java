package com.tsnguyentanphat.sathach.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.model.Event;

import java.io.IOException;
import java.io.InputStream;

public class EventAdapter extends ArrayAdapter<Event> {
    Activity context;
    int resource;
    public EventAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource, null);

        ImageView imgHinh = customView.findViewById(R.id.imgHinh);
        TextView txtTitle = customView.findViewById(R.id.txtTitle);
        TextView txtIntro = customView.findViewById(R.id.txtIntro);
        TextView txtStatus = customView.findViewById(R.id.txtStatus);

        Event event = getItem(position);
        imgHinh.setImageDrawable(getDrawable(event.getImgName()));
        txtTitle.setText(event.getTitle());
        txtIntro.setText(event.getIntro());
        txtStatus.setText(event.getStatus());

        return customView;
    }

    private Drawable getDrawable(String imgName) {
        Drawable d;
        try {
            InputStream inputStream = getContext().getAssets().open("image/"+imgName);
            d = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return d;
    }
}

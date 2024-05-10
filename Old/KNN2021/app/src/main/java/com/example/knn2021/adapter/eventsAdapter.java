package com.example.knn2021.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knn2021.R;
import com.example.knn2021.modle.events;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;

public class eventsAdapter extends ArrayAdapter<events> {
    Activity context;
    int resource;
    public eventsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        ImageView eventsPic = customView.findViewById(R.id.events_img);
        TextView eventsTitle = customView.findViewById(R.id.events_title);
        TextView eventsDesc = customView.findViewById(R.id.events_desc);
//        TextView eventsStatus = customView.findViewById(R.id.events_status);

        events e = this.getItem(position);
        eventsTitle.setText(e.getEventsTitle());
        eventsDesc.setText(e.getEventsDesc());
        eventsPic.setImageDrawable(getImage(e.getEventsPic()));

        return customView;
    }

    public Drawable getImage(String name){
        Drawable drawable = null;
        try {
            InputStream ims = getContext().getAssets().open(name);
            drawable = Drawable.createFromStream(ims, null);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return drawable;
    }
}

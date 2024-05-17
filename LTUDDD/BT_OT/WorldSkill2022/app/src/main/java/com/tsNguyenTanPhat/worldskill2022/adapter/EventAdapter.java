package com.tsNguyenTanPhat.worldskill2022.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsNguyenTanPhat.worldskill2022.R;
import com.tsNguyenTanPhat.worldskill2022.model.Event;

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
        View customView = this.context.getLayoutInflater().inflate(this.resource,null);

        ImageView imgPIC = customView.findViewById(R.id.imgPIC);
        TextView txtTitle = customView.findViewById(R.id.txtTitle);
        TextView txtContent = customView.findViewById(R.id.txtContent);
        TextView txtStatus = customView.findViewById(R.id.txtStatus);

        Event event = getItem(position);
        txtTitle.setText(event.getTitle());
        txtContent.setText(event.getContent());


        return super.getView(position, convertView, parent);
    }
}

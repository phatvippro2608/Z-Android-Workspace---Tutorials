package com.ntp.ontaplistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        View customView = inflater.inflate(this.resource,null);
        ImageView eventsImg = customView.findViewById(R.id.events_img);
        TextView eventsTitle = customView.findViewById(R.id.events_title);
        TextView eventsDesc = customView.findViewById(R.id.events_desc);
        TextView eventsStatus = customView.findViewById(R.id.events_status);

        events e = this.getItem(position);
        eventsTitle.setText(e.getEventsTitle());
        eventsDesc.setText(e.getEventsDesc());
        eventsStatus.setText(e.getStatus());


        return customView;

    }


}

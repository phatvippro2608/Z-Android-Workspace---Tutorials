package com.tsnguyentanphat.sathach.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.model.Ticket;

public class TicketAdapter extends ArrayAdapter<Ticket> {
    Activity context;
    int resource;
    public TicketAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource, null);

        TextView txtName = customView.findViewById(R.id.txtName);
        TextView txtSeat = customView.findViewById(R.id.txtSeat);

        Ticket ticket = getItem(position);
        txtName.setText(ticket.getName());
        txtSeat.setText(ticket.getSeat());

        return customView;
    }
}

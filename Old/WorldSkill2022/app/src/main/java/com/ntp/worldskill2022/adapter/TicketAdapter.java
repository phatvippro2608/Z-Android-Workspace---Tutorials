package com.ntp.worldskill2022.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ntp.worldskill2022.R;
import com.ntp.worldskill2022.model.Ticket;

public class TicketAdapter extends ArrayAdapter<Ticket> {
    Activity context; int resource;
    public TicketAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    TextView txtTicketName, txtTicketSeat;
    RelativeLayout layoutTicket;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        txtTicketName = customView.findViewById(R.id.txtTicketName);
        txtTicketSeat = customView.findViewById(R.id.txtTicketSeat);
        layoutTicket = customView.findViewById(R.id.layoutTicket);

        Ticket ticket = getItem(position);
        txtTicketName.setText(ticket.getTicketAudienceName());
        txtTicketSeat.setText(ticket.getTicketSeat());

        return customView;
    }
}

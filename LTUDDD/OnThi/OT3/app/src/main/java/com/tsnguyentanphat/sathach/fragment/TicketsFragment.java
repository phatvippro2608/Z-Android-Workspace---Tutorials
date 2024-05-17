package com.tsnguyentanphat.sathach.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.adapter.TicketAdapter;
import com.tsnguyentanphat.sathach.model.Ticket;

public class TicketsFragment extends Fragment {
    View view;
    ListView lvOpenCer, lvCloseCer;
    Button btnNewTicket;
    TicketCreateFragment ticketCreateFragment = new TicketCreateFragment();
    TicketAdapter ticketAdapter;
    //TicketDAO ticketDAO = new TicketDAO();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        btnNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketCreateFragment).commit();
            }
        });
    }

    private void addControls() {
        lvCloseCer = view.findViewById(R.id.lvCloseCer);
        lvOpenCer = view.findViewById(R.id.lvOpenCer);
        btnNewTicket = view.findViewById(R.id.btnNewTicket);
        ticketAdapter =  new TicketAdapter(
                getActivity(),
                R.layout.item_ticket
        );
        lvOpenCer.setAdapter(ticketAdapter);
        lvCloseCer.setAdapter(ticketAdapter);

    }
}
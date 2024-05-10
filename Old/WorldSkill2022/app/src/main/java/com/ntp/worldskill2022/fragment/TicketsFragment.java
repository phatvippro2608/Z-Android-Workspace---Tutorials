package com.ntp.worldskill2022.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ntp.worldskill2022.MainActivity;
import com.ntp.worldskill2022.R;
import com.ntp.worldskill2022.adapter.TicketAdapter;
import com.ntp.worldskill2022.database.Database;
import com.ntp.worldskill2022.databinding.FragmentTicketsBinding;
import com.ntp.worldskill2022.model.Ticket;

import java.util.ArrayList;

public class TicketsFragment extends Fragment {
    FragmentTicketsBinding binding;

//    AppCompatButton btnCreateNewTicket;
//    ListView lvOpenTicket, lvCloseTicket;

    public final static String TICKET_KEY = "fromTicketFragment";
    ArrayAdapter arrayAdapter;
    TicketAdapter ticketAdapter, ticketAdapter2;
    Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_tickets, container, false);
//        btnCreateNewTicket = view.findViewById(R.id.btnCreateTicket);
//        lvOpenTicket = view.findViewById(R.id.lvOpeningCeremonyTickets);
//        lvCloseTicket = view.findViewById(R.id.lvClosingCeremonyTickets);

//        database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.DBVersion);
//        database.insertData("JOHN");
//        database.insertData("Bobby");
//        database.insertData("Lena");
//        ArrayList<String> arrayListTicket = new ArrayList<>();
//        arrayListTicket = database.getTicketData();
//        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayListTicket);

        ticketAdapter = new TicketAdapter(getActivity(), R.layout.customlayout_ticket);
        ticketAdapter2 = new TicketAdapter(getActivity(), R.layout.customlayout_ticket);

        binding.lvOpeningCeremonyTickets.setAdapter(ticketAdapter);
        binding.lvClosingCeremonyTickets.setAdapter(ticketAdapter2);
        database = new Database(getActivity(), Database.DATABASE_NAME, null, Database.DBVersion);
        ArrayList<Ticket> tickets = database.getTicket();
        for(Ticket ticket:tickets){
            if(ticket.getTicketType().equals("Opening Ceremony")){
                ticketAdapter.add(ticket);
            }else {
                ticketAdapter2.add(ticket);
            }
        }



        setEvents();
        return view;
    }

    private void setEvents() {
        binding.btnCreateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TicketCreateFragment ticketCreateFragment = new TicketCreateFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.container, ticketCreateFragment).commit();
            }
        });
        binding.lvOpeningCeremonyTickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ticket tk = ticketAdapter.getItem(i);
//                TicketDetailsFragment ticketDetailsFragment = new TicketDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ticket", tk);

//                getParentFragmentManager().setFragmentResult(TICKET_KEY, bundle);
                TicketDetailsFragment.tk2 = tk;
                getParentFragmentManager().beginTransaction().replace(R.id.container, MainActivity.ticketDetailsFragment).commit();
            }
        });
        binding.lvClosingCeremonyTickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ticket tk = ticketAdapter2.getItem(i);

                TicketDetailsFragment.tk2 = tk;
                getParentFragmentManager().beginTransaction().replace(R.id.container, MainActivity.ticketDetailsFragment).commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
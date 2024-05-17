package com.tsnguyentanphat.sathach.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tsnguyentanphat.sathach.activity.MainActivity;
import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.adapter.EventAdapter;
import com.tsnguyentanphat.sathach.dao.EventDAO;
import com.tsnguyentanphat.sathach.model.Event;

import java.util.ArrayList;

public class EventFragment extends Fragment {
    View view;
    ListView lvEvent;
    TextView txtAll, txtRead, txtUnread;
    EventAdapter eventAdapter;
    EventDAO eventDAO = new EventDAO();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event, container, false);
        addControls();
        return view;
    }

    private void addControls() {
        lvEvent = view.findViewById(R.id.lvEvent);
        txtAll = view.findViewById(R.id.txtAll);
        txtRead = view.findViewById(R.id.txtRead);
        txtUnread = view.findViewById(R.id.txtUnread);
        eventAdapter = new EventAdapter(
                getActivity(),
                R.layout.item_event
        );
        lvEvent.setAdapter(eventAdapter);
        Event event = new Event();
        event.setImgName("h1.jpg");
        event.setTitle("fhsdifldslf");
        event.setIntro("hfksdflkdsklfkdsfjldskfkdslfkl");
        event.setStatus("Unread");
        eventAdapter.add(event);

        ArrayList<Event>events=eventDAO.getEvent(getActivity());
        eventAdapter.addAll(events);

    }

}
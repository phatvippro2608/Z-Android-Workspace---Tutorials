package com.tsnguyentanphat.example1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tsnguyentanphat.example1.R;
import com.tsnguyentanphat.example1.adapter.EventsAdapter;
import com.tsnguyentanphat.example1.dao.EventsDAO;
import com.tsnguyentanphat.example1.model.Events;

public class EventsFragment extends Fragment {
    View view;
    ListView lvEvents;
    EventsAdapter eventsAdapter;
    EventsDAO eventsDAO = new EventsDAO();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
    }

    private void addControls() {
        lvEvents = view.findViewById(R.id.lvEvents);
        eventsAdapter = new EventsAdapter(
                getActivity(),
                R.layout.listview_events_item
        );
        lvEvents.setAdapter(eventsAdapter);
        eventsAdapter.add(eventsDAO.getEventsTest());
    }
}
package com.tsnguyentanphat.sathach.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.adapter.EventsAdapter;
import com.tsnguyentanphat.sathach.model.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EventsFragment extends Fragment {
    View view;
    ListView lvEvents;
    TextView txtAll, txtUnread, txtRead;
    EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
    EventsAdapter eventsAdapter;
    int count = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        addControls();
        addEvents();
        return view ;
    }

    private void addEvents() {
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count+=1;
                Event e = eventsAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("title",e.getTitle());
                bundle.putString("imgName",e.getImgName());
                bundle.putString("description",e.getDescription());
                eventDetailsFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventDetailsFragment).commit();
            }
        });
    }

    private void addControls() {
        lvEvents = view.findViewById(R.id.lvEvents);
        txtAll = view.findViewById(R.id.txtAll);
        txtUnread = view.findViewById(R.id.txtUnread);
        txtRead = view.findViewById(R.id.txtRead);
        setEventList();
    }

    private void setEventList() {
        eventsAdapter = new EventsAdapter(
                getActivity(),
                R.layout.item_event_layout
        );
        lvEvents.setAdapter(eventsAdapter);
        ArrayList<Event>events = new ArrayList<>();
        events = getEvents();
        eventsAdapter.addAll(events);
    }

    private ArrayList<Event> getEvents() {
        ArrayList<Event>events = new ArrayList<>();
        try {
            InputStream inputStream = getActivity().getAssets().open("events_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            JSONArray arrEvent = root.getJSONArray("events");
            for(int i = 0; i<arrEvent.length(); i++){
                JSONObject jsonObject = arrEvent.getJSONObject(i);
                Event event = new Event();
                event.setImgName(jsonObject.getString("imageName"));
                event.setTitle(jsonObject.getString("title"));
                event.setIntroduction(jsonObject.getString("introduction"));
                event.setDescription(jsonObject.getString("Description"));
                event.setStatus(jsonObject.getString("status"));
                events.add(event);
            }

        } catch (Exception e) {
            Log.e("ERROR:","JSON: "+e);
        }

        return events;
    }

    private Drawable getImageDrawable(String imageName) {
        Drawable d;
        try {
            InputStream inputStream = getActivity().getAssets().open("image/"+imageName);
            d = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return d;
    }
}
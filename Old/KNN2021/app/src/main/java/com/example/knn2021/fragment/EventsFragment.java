package com.example.knn2021.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knn2021.R;
import com.example.knn2021.adapter.eventsAdapter;
import com.example.knn2021.connection.Connection;
import com.example.knn2021.connection.EventsManager;
import com.example.knn2021.modle.events;
import com.example.knn2021.ui.HomeActivity;
import com.example.knn2021.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventsFragment extends Fragment {
    TextView txtAll, txtUnread, txtRead;
    ListView lvEvents;
    ArrayAdapter<events>eventsArrayAdapter ;
    EventsDetailFragment eventsDetailFragment = new EventsDetailFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        txtAll = view.findViewById(R.id.btnAll);
        txtUnread = view.findViewById(R.id.btnUnread);
        txtRead = view.findViewById(R.id.btnRead);
        lvEvents = view.findViewById(R.id.lvEvents);
        Bundle bundle = new Bundle();
        bundle.putInt("count", 403);
//        getParentFragmentManager().setFragmentResult("events",bundle);
        HomeActivity homeActivity = new HomeActivity();
        homeActivity.sendBundle("events",bundle);
        txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.container, eventsDetailFragment).commit();
            }
        });
        eventsArrayAdapter = new eventsAdapter(getActivity(), R.layout.layout_events);
        lvEvents.setAdapter(eventsArrayAdapter);
        EventsManager eventsManager = new EventsManager();
        ArrayList<events> eventsArrayList = eventsManager.getEvents(getActivity());
        for(events e : eventsArrayList){
            eventsArrayAdapter.add(e);
        }
        return  view;
    }


    private boolean putEvents(String param, String jsonName, events e){
        try {
            JSONObject jsonObjectmain;
            JSONArray jsonArray;
            Connection connection = new Connection();
            String jsonString = connection.mReadJsonData(param, getActivity());
            jsonObjectmain = new JSONObject(jsonString);
            jsonArray = jsonObjectmain.getJSONArray(jsonName);
            JSONObject events_status = new JSONObject();
            boolean flag = false;

            for(int ind=0; ind<jsonArray.length(); ind++){
                events_status = (JSONObject) jsonArray.get(ind);
                if(events_status.get("title").equals(e.getEventsTitle())){
                    jsonArray.remove(ind);
                    int count = Integer.parseInt(events_status.get("count").toString())+1;

                    events_status.put("count",count);
                    events_status.put("status","Read");


                    Toast.makeText(getActivity(), events_status.toString(), Toast.LENGTH_SHORT).show();
                    jsonArray.put(events_status);
                    jsonObjectmain.put(jsonName, jsonArray);
                    connection.mCreateAndSaveFile(param, jsonObjectmain.toString(), getActivity());

                    flag = true;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("e",e);
                    bundle.putInt("count", count);
                    this.setArguments(bundle);
                }
            }
            return true;
        }catch(JSONException je){
                return false;
        }
    }
}
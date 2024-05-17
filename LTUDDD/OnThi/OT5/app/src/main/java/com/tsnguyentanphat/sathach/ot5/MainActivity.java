package com.tsnguyentanphat.sathach.ot5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void addEvents() {
        lvEvent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Event event = eventAdapter.getItem(position);
                bundle.putInt("id",event.getId());
                bundle.putInt("count",(event.getCount()+1));
                bundle.putString("imgName", event.getImgName());
                bundle.putString("title",event.getTitle());
                bundle.putString("description", event.getDescription());
                setStatus(event.getId());
                setCount(event.getId(),event.getCount());
                eventDetailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventDetailFragment).commit();
            }
        });
        txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllEvent();
            }
        });
        txtRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReadEvent();
            }
        });
        txtUnread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUnreadEvent();
            }
        });
    }

    private void setAllEvent() {
        ArrayList<Event> events = new ArrayList<>();
        for(int i = 0; i<arrEvent.length(); i++){
            try {
                JSONObject jsonObject = arrEvent.getJSONObject(i);
                Event event = new Event();
                event.setId(i);
                event.setTitle(jsonObject.getString("title"));
                event.setImgName(jsonObject.getString("imageName"));
                event.setIntro(jsonObject.getString("introduction"));
                event.setDescription(jsonObject.getString("Description"));
                event.setStatus(jsonObject.getString("status"));
                event.setCount(jsonObject.getInt("count"));
                events.add(event);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        eventAdapter.clear();
        eventAdapter.addAll(events);
    }

    private void setReadEvent() {
        ArrayList<Event>events = new ArrayList<>();
        for(int i = 0; i<arrEvent.length(); i++){
            try {
                JSONObject jsonObject = arrEvent.getJSONObject(i);
                if(jsonObject.getString("status").equals("Read")){
                    Event event = new Event();
                    event.setId(i);
                    event.setTitle(jsonObject.getString("title"));
                    event.setImgName(jsonObject.getString("imageName"));
                    event.setIntro(jsonObject.getString("introduction"));
                    event.setDescription(jsonObject.getString("Description"));
                    event.setStatus(jsonObject.getString("status"));
                    event.setCount(jsonObject.getInt("count"));
                    events.add(event);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        eventAdapter.clear();
        eventAdapter.addAll(events);
    }

    private void setUnreadEvent() {
        ArrayList<Event>events = new ArrayList<>();
        for(int i = 0; i<arrEvent.length(); i++){
            try {
                JSONObject jsonObject = arrEvent.getJSONObject(i);
                if(jsonObject.getString("status").equals("Unread")){
                    Event event = new Event();
                    event.setId(i);
                    event.setTitle(jsonObject.getString("title"));
                    event.setImgName(jsonObject.getString("imageName"));
                    event.setIntro(jsonObject.getString("introduction"));
                    event.setDescription(jsonObject.getString("Description"));
                    event.setStatus(jsonObject.getString("status"));
                    event.setCount(jsonObject.getInt("count"));
                    events.add(event);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        eventAdapter.clear();
        eventAdapter.addAll(events);
    }
}
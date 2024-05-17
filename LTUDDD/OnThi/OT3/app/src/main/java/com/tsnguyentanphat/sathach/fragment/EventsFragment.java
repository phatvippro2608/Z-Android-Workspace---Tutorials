package com.tsnguyentanphat.sathach.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.sathach.activity.R;
import com.tsnguyentanphat.sathach.adapter.EventAdapter;
import com.tsnguyentanphat.sathach.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EventsFragment extends Fragment {
    View view;
    ListView lvEvent;
    TextView txtAll, txtRead, txtUnread;
    EventAdapter eventAdapter;
    JSONArray arrEvent;
    int install=0;
    EventDetailFragment eventDetailFragment = new EventDetailFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events, container, false);
        createFile();
        addControls();
        addEvents();
        return view;
    }

    private void createFile() {
        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("json.json", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        ArrayList<Event>events = new ArrayList<>();
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

    private void setCount(int position, int count) {
        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("json.json", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            arrEvent.getJSONObject(position).put("count",count+1);
            JSONObject newJSON = new JSONObject();
            newJSON.put("events",arrEvent);
            outputStreamWriter.write(newJSON.toString());
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e("ERROR:","JSON: "+e);
        }
    }

    private void setStatus(int position) {
        try {
            FileOutputStream fileOutputStream = getActivity().openFileOutput("json.json", getActivity().MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            arrEvent.getJSONObject(position).put("status","Read");
            JSONObject newJSON = new JSONObject();
            newJSON.put("events",arrEvent);
            outputStreamWriter.write(newJSON.toString());
            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e("ERROR:","JSON: "+e);
        }
    }

    private void addControls() {
        lvEvent = view.findViewById(R.id.lvEvent);
        txtAll = view.findViewById(R.id.txtAll);
        txtUnread = view.findViewById(R.id.txtUnread);
        txtRead = view.findViewById(R.id.txtRead);
        eventAdapter = new EventAdapter(
                getActivity(),
                R.layout.item_event
        );
        lvEvent.setAdapter(eventAdapter);
        setEventData();
    }

    private void setEventData() {
        ArrayList<Event>events = getEvent();
        eventAdapter.clear();
        eventAdapter.addAll(events);
    }

    private ArrayList getEvent() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Event>events = new ArrayList<>();
        try {
            FileInputStream fileInputStream = getActivity().openFileInput("json.json");
            int size = fileInputStream.available();
            byte[]buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
//
//            String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat.sathach";
//            File newFile = new File(url);
//            newFile.mkdirs();
//
//
//            FileReader fileReader = new FileReader(url+"/events_data.txt");
//
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            String line = bufferedReader.readLine();
//            while (line!=null){
//                stringBuilder.append(line).append("\n");
//                line = bufferedReader.readLine();
//            }
//            fileReader.close();
//            String json = stringBuilder.toString();
            String json = new String(buffer, StandardCharsets.UTF_8);
            if(json.trim().equals("")){
                jsonToStorage();
                json = getAgain();
            }

            JSONObject root = new JSONObject(json);
            arrEvent = root.getJSONArray("events");
            for(int i = 0; i<arrEvent.length(); i++){
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
            }
        } catch (Exception e) {
            Log.e("ERROR:","JSON1: "+e);
        }
        return events;
    }

    private String getAgain() {
//        String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat.sathach";
//        StringBuilder stringBuilder = new StringBuilder();
        String json;
//        try {
//            FileReader fileReader = new FileReader(url+"/events_data.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while(line!=null){
//                stringBuilder.append(line).append("\n");
//                line = bufferedReader.readLine();
//            }
//            json = stringBuilder.toString();
//            fileReader.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        try {
            FileInputStream fileInputStream = getActivity().openFileInput("json.json");
            int size = fileInputStream.available();
            byte[]buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            json = new String(buffer,StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }


    private void jsonToStorage() {
        try {
            InputStream inputStream = getActivity().getAssets().open("events_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

//            String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat.sathach";
//            File newFile = new File(url);
//            newFile.mkdirs();
            FileOutputStream fileOutputStream = getActivity().openFileOutput("json.json", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(json);
            outputStreamWriter.close();
//            FileWriter fileWriter = new FileWriter(url+"/events_data.txt");
//            fileWriter.write(json);
//            fileWriter.close();
        } catch (Exception e) {
            Log.e("ERROR:","JSON: "+e);
        }
    }
}
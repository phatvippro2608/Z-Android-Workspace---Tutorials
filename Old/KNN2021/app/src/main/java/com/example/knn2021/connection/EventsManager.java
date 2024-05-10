package com.example.knn2021.connection;

import android.content.Context;
import android.util.Log;

import com.example.knn2021.modle.events;
import com.example.knn2021.ui.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EventsManager {
    public EventsManager() {
    }

    Connection connection = new Connection();
    public ArrayList<events> getEvents(Context view){
        ArrayList<events> arrayListEvents = new ArrayList<>();
        try {
            InputStream inputStream = view.getAssets().open("events_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            JSONArray array = root.getJSONArray("events");
//            JSONArray array = new JSONArray(json);

            for(int i = 0; i<array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                events e = new events();
                e.setEventsTitle(jsonObject.get("title").toString());
                e.setEventsDesc(jsonObject.get("desc").toString());
                e.setEventsPic(jsonObject.get("pic1").toString());
                e.setEventsPic2(jsonObject.get("pic2").toString());
                e.setEventsPic3(jsonObject.get("pic3").toString());
                arrayListEvents.add(e);
            }
            return arrayListEvents;
        } catch (Exception e) {
            return null;
        }
    }

    public void setEventStatus(String id, int count, String status){
        String jsonString = connection.mReadJsonData("events_status.json", new HomeActivity());
        try {
            JSONObject jsonObjectMain = new JSONObject(jsonString);
            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObjectMain.getJSONArray("events_status");
            for(int ind = 0; ind<jsonArray.length(); ind++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(ind);
                if(jsonObject.get("title").equals(id)){
                    jsonArray.remove(ind);
                    jsonObject.put("count", count);
                    jsonObject.put("status", status);
                    jsonArray.put(jsonObject);
                    jsonObjectMain.put("events_status", jsonArray);
                    break;
                }
            }
        } catch (JSONException ex) {
        }

    }

    public void createNewEventsStatus(String id, int count, String status){
        String jsonString = connection.mReadJsonData("events_status.json", new HomeActivity());
        try {
            JSONObject jsonObjectMain = new JSONObject(jsonString);
            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObjectMain.getJSONArray("events_status");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title",id);
            jsonObject.put("count",count);
            jsonObject.put("status",status);
            jsonArray.put(jsonObject);
            jsonObjectMain.put("events_status",jsonArray);
        } catch (JSONException ex) {
        }
    }

    public boolean checkEvents(String id){
        String jsonString = connection.mReadJsonData("events_status.json", new HomeActivity());
        try {
            JSONObject jsonObjectMain = new JSONObject(jsonString);
            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObjectMain.getJSONArray("events_status");
            for(int ind = 0; ind<jsonArray.length(); ind++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(ind);
                if(jsonObject.get("title").equals(id)){
                    return true;
                }
            }
            return false;
        } catch (JSONException ex) {
            return  false;
        }
    }
}

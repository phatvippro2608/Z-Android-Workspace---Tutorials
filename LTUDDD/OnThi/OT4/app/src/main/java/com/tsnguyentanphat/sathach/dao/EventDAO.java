package com.tsnguyentanphat.sathach.dao;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import com.tsnguyentanphat.sathach.model.Event;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class EventDAO {
    public ArrayList getEvent(Activity activity){
        ArrayList<Event>events = new ArrayList<>();
        checkData(activity);
        try {
            String url = Environment.getExternalStorageDirectory()+"/Android/data/com3.tsnguyentanphat.sathach";
            File newFile = new File(url+"/json_events.txt");
            newFile.mkdirs();

            FileReader fileReader = new FileReader(url+"/json_events.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while(line!=null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            JSONObject root = new JSONObject(stringBuilder.toString());
            JSONArray arrEvent = root.getJSONArray("events");
            for(int i = 0; i< arrEvent.length(); i++){
                JSONObject jsonObject = arrEvent.getJSONObject(i);
                Event event = new Event();
                event.setId(i);
                event.setImgName(jsonObject.getString("imgName"));
                event.setTitle(jsonObject.getString("title"));
                event.setIntro(jsonObject.getString("intro"));
                event.setStatus(jsonObject.getString("status"));
                event.setCount(jsonObject.getInt("count"));
                event.setDescription(jsonObject.getString("description"));
                events.add(event);
            }
        } catch (Exception e) {
            Log.e("ERROR:","JSON: "+e);
        }


        return events;
    }

    private void checkData(Activity activity) {
        String url = Environment.getExternalStorageDirectory()+"/Android/data/com3.tsnguyentanphat.sathach";
        File newFile = new File(url);
        newFile.mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(url+"/json_events.txt");
            fileWriter.close();

            FileReader fileReader = new FileReader(url+"/json_events.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while(line!=null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            String json = stringBuilder.toString();
            if(json.length()<10){
                jsonToStorage(activity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void jsonToStorage(Activity activity) {

        InputStream inputStream = null;
        try {
            inputStream = activity.getAssets().open("events_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            JSONArray arrEvent = root.getJSONArray("events");

            JSONObject newJSON = new JSONObject();
            newJSON.put("events",arrEvent);

            String url = Environment.getExternalStorageDirectory()+"/Android/data/com3.tsnguyentanphat.sathach";
            FileWriter fileWriter = new FileWriter(url+"/json_events.txt");
            fileWriter.write(newJSON.toString());
            fileWriter.close();



        } catch (Exception e) {
            Log.e("ERROR","WRITE JSON:"+e);
        }
    }
}

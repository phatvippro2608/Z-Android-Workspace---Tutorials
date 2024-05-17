package com.tsnguyentanphat.sathach.writejson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tsnguyentanphat.sathach.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Event>events=new ArrayList<>();
    TextView txt1;
    EditText edtNewTitle, edtChangeStatus;
    Button btnChangeTitle, btnChangeEditText, btnChangeStatus;
    JSONArray arrEvent;
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnChangeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setJSON();
                try {
                    JSONObject jsonObject = arrEvent.getJSONObject(arrEvent.length()-1);
                    txt1.setText(jsonObject.getString("title"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnChangeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat";
                    FileReader fileReader = new FileReader(url+"/output.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    while(line!=null){
                        stringBuilder.append(line).append("\n");
                        line = bufferedReader.readLine();
                    }
                    json = stringBuilder.toString();
                    JSONObject root = new JSONObject(json);
                    JSONArray arrData = root.getJSONArray("events");
                    String s = arrData.getJSONObject(0).getString("title");
                    edtNewTitle.setText(s);
                } catch (Exception e) {
                    Log.e("ERROR:", "JSON: readJSON"+e);
                }
            }
        });
        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat";
                StringBuilder stringBuilder = new StringBuilder();
                String js;
                try {
                    FileReader fileReader = new FileReader(url+"/output.txt");
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    while(line!=null){
                        stringBuilder.append(line).append("\n");
                        line = bufferedReader.readLine();
                    }
                    js = stringBuilder.toString();
                    JSONObject root = new JSONObject(js);
                    JSONArray array = root.getJSONArray("events");
                    array.getJSONObject(0).put("status", "Read");
                    String s2 = array.getJSONObject(0).getString("status");
                    JSONObject NewJSONOb = new JSONObject();
                    NewJSONOb.put("events",array);
                    edtChangeStatus.setText(NewJSONOb.toString());
                } catch (Exception e) {
                    Log.e("ERROR:","JSON: "+e);
                }
            }
        });
    }

    private void setJSON() {
        try {
            InputStream inputStream = getAssets().open("events_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject root = new JSONObject(json);
            arrEvent = root.getJSONArray("events");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", "Mua He Xanh");
            jsonObject.put("imageName", "h1.jpg");
            jsonObject.put("introduction", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit.");
            jsonObject.put("Description", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna. Nunc viverra imperdiet enim. Fusce est.");
            jsonObject.put("status", "Unread");
            arrEvent.put(jsonObject);
            JSONObject newJsonObject = new JSONObject();
            newJsonObject.put("events",arrEvent);
            String url = Environment.getExternalStorageDirectory()+"/Android/data/com.tsnguyentanphat";
            File newFile = new File(url);
            newFile.mkdirs();
            FileWriter fileWriter = new FileWriter(url+"/output.json");
            fileWriter.write(newJsonObject.toString());
            fileWriter.close();
        } catch (Exception e) {
            Log.e("ERROR:", "JSON: "+e);
        }
    }

    private void addControls() {
        txt1 = findViewById(R.id.txt1);
        btnChangeTitle = findViewById(R.id.btnChangeTitle);
        edtNewTitle = findViewById(R.id.edtNewTitle);
        btnChangeEditText = findViewById(R.id.btnChangEditText);
        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        edtChangeStatus = findViewById(R.id.edtChangeStatus);
        readJSON();
        Event event0 = events.get(0);
        String s = event0.getTitle();
        txt1.setText(event0.getTitle()+"\n");
        txt1.append(event0.getIntroduction());
    }

    private void readJSON() {
        try {
            InputStream inputStream = getAssets().open("events_data.json");
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
                event.setId(i);
                event.setTitle(jsonObject.getString("title"));
                event.setIntroduction(jsonObject.getString("introduction"));
                event.setImageName(jsonObject.getString("imageName"));
                event.setDescription(jsonObject.getString("Description"));
                event.setStatus(jsonObject.getString("status"));
                events.add(event);
            }

        } catch (Exception e) {
            Log.e("ERROR:", "JSON: "+e);
        }
    }
}
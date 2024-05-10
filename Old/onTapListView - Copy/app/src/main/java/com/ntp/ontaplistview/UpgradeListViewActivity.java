package com.ntp.ontaplistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpgradeListViewActivity extends AppCompatActivity {

    Connection connection = new Connection();
    ListView lvEvents;
    eventsAdapter eventsAdapter;
    String jsonString="";
    private void fakeData(){
        eventsAdapter.add(new events("","","","Mùa hè xanh","Mùa hè xanh","Read",0));
        eventsAdapter.add(new events("","","","Hiến màu tình nguyện","Hiến màu tình nguyện","Read",0));
        eventsAdapter.add(new events("","","","Tư vấn tuyển sinh","Tư vấn tuyển sinh","Read",0));
        eventsAdapter.add(new events("","","","Tiếp sức mùa thi","Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi Tiếp sức mùa thi","Read",0));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_list_view);
        lvEvents = findViewById(R.id.lvEvents);
        eventsAdapter = new eventsAdapter(this, R.layout.customlayout_events);
        lvEvents.setAdapter(eventsAdapter);
        fakeData();
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                events e = eventsAdapter.getItem(i);
                if(connection.mReadJsonData("events_status.json", UpgradeListViewActivity.this).equals("Error")){
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonEvent = new JSONObject();
                    try{
                        jsonObject.put("title", e.getEventsTitle());
                        jsonObject.put("status", "Read");
                        jsonObject.put("count", "1");
                        jsonArray.put(jsonObject);
                        jsonEvent.put("events",jsonArray);

                    }catch (JSONException jsonException){
                    }
                    connection.mCreateAndSaveFile("events_status.json", jsonEvent.toString(), UpgradeListViewActivity.this);
                    Toast.makeText(UpgradeListViewActivity.this, "Da them", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonEvent = new JSONObject();
                    jsonString = connection.mReadJsonData("events_status.json", UpgradeListViewActivity.this);
                    try{
                        jsonObject = new JSONObject(jsonString);
                        jsonArray = jsonObject.getJSONArray("events");
                        JSONObject events_status = new JSONObject();
                        boolean flag = false;
                        for(int ind=0; ind<jsonArray.length(); ind++){
                            events_status = (JSONObject) jsonArray.get(ind);
                            if(events_status.get("title").equals(e.getEventsTitle())){
                                jsonArray.remove(ind);
                                int count = Integer.parseInt(events_status.get("count").toString())+1;
                                events_status.put("count",count);
                                Toast.makeText(UpgradeListViewActivity.this, events_status.toString(), Toast.LENGTH_SHORT).show();
                                jsonArray.put(events_status);
                                jsonEvent.put("events",jsonArray);
                                connection.mCreateAndSaveFile("events_status.json", jsonEvent.toString(), UpgradeListViewActivity.this);

                                flag = true;
                                Intent intent = new Intent(UpgradeListViewActivity.this, EventsDetailsActivity.class);
                                intent.putExtra("title",e.getEventsTitle());
                                intent.putExtra("desc",e.getEventsDesc());
                                intent.putExtra("status",e.getStatus());
                                intent.putExtra("count",count);
                                startActivity(intent);
                                break;
                            }
                        }
                        if(!flag){
                            JSONObject new_events_status = new JSONObject();
                            new_events_status.put("title", e.getEventsTitle());
                            new_events_status.put("status", "Read");
                            new_events_status.put("count", "1");
                            Toast.makeText(UpgradeListViewActivity.this, new_events_status.toString(), Toast.LENGTH_SHORT).show();
                            jsonArray.put(new_events_status);
                            jsonEvent.put("events",jsonArray);
                            connection.mCreateAndSaveFile("events_status.json", jsonEvent.toString(), UpgradeListViewActivity.this);
                            Intent intent = new Intent(UpgradeListViewActivity.this, EventsDetailsActivity.class);
                            intent.putExtra("title",e.getEventsTitle());
                            intent.putExtra("desc",e.getEventsDesc());
                            intent.putExtra("status",e.getStatus());
                            intent.putExtra("count",1);
                            startActivity(intent);
                        }

                    }catch (JSONException jsonException){

                    }

                }







            }
        });
    }
}
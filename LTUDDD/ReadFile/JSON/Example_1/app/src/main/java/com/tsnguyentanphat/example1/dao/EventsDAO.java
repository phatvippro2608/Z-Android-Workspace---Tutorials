package com.tsnguyentanphat.example1.dao;

import com.tsnguyentanphat.example1.model.Events;

import java.util.ArrayList;

public class EventsDAO {
    public Events getEventsTest(){
        Events events = new Events();
        events.setId(1);
        events.setTitle("Annual Charity Run");
        events.setImageUrl("http://dfd714qjvm64t.cloudfront.net/wp-content/uploads/sites/14/2018/09/2018-Charity-Run-poster.jpg");
        events.setIntroduction("Join us for the annual charity run to raise funds for local shelters and rescue organizations. Runners of all levels are welcome to participate!");
        events.setStatus("Unread");
        return events;
    }
//    public ArrayList getEvents(){
//        ArrayList<Events>events=new ArrayList<>();
//        String jsonText = readText()
//    }
}

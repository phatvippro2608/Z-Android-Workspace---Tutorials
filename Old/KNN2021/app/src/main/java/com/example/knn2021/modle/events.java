package com.example.knn2021.modle;

import java.io.Serializable;

public class events implements Serializable {
    private String eventsPic;
    private String eventsPic2;
    private String eventsPic3;
    private String eventsTitle;
    private  String eventsDesc;
    private  String status;
    private int count;

    public events() {
    }

    public events(String eventsPic, String eventsPic2, String eventsPic3, String eventsTitle, String eventsDesc, String status, int count) {
        this.eventsPic = eventsPic;
        this.eventsPic2 = eventsPic2;
        this.eventsPic3 = eventsPic3;
        this.eventsTitle = eventsTitle;
        this.eventsDesc = eventsDesc;
        this.status = status;
        this.count = count;
    }


    public String getEventsPic() {
        return eventsPic;
    }

    public void setEventsPic(String eventsPic) {
        this.eventsPic = eventsPic;
    }

    public String getEventsPic2() {
        return eventsPic2;
    }

    public void setEventsPic2(String eventsPic2) {
        this.eventsPic2 = eventsPic2;
    }

    public String getEventsPic3() {
        return eventsPic3;
    }

    public void setEventsPic3(String eventsPic3) {
        this.eventsPic3 = eventsPic3;
    }

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public String getEventsDesc() {
        return eventsDesc;
    }

    public void setEventsDesc(String eventsDesc) {
        this.eventsDesc = eventsDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

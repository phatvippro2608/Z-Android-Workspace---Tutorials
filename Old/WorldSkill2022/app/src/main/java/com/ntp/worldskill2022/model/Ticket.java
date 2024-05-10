package com.ntp.worldskill2022.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    private int id;
    private String ticketType;
    private String ticketAudienceName;
    private String ticketTime;
    private  String ticketSeat;
    private Bitmap ticketPic;

    public Ticket() {
    }

    public Ticket(int id, String ticketType, String ticketAudienceName, String ticketTime, String ticketSeat, Bitmap ticketPic) {
        this.id = id;
        this.ticketType = ticketType;
        this.ticketAudienceName = ticketAudienceName;
        this.ticketTime = ticketTime;
        this.ticketSeat = ticketSeat;
        this.ticketPic = ticketPic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketAudienceName() {
        return ticketAudienceName;
    }

    public void setTicketAudienceName(String ticketAudienceName) {
        this.ticketAudienceName = ticketAudienceName;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getTicketSeat() {
        return ticketSeat;
    }

    public void setTicketSeat(String ticketSeat) {
        this.ticketSeat = ticketSeat;
    }

    public Bitmap getTicketPic() {
        return ticketPic;
    }

    public void setTicketPic(Bitmap ticketPic) {
        this.ticketPic = ticketPic;
    }
}

//package com.tsnguyentanphat.sathach.dao;
//
//import android.os.Environment;
//
//import com.tsnguyentanphat.sathach.model.Ticket;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class TicketDAO {
//    public TicketDAO() {
//        String url = Environment.getExternalStorageDirectory()+"/Android/data/com.ntp";
//        File folder = new File(url);
//        folder.mkdirs();
//        File newFile = new File(url+"ticket.txt");
//        try {
//            newFile.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ArrayList getOpenCer(){
//        ArrayList<Ticket>tickets=new ArrayList<>();
//
//
//        return tickets;
//    }
//    public ArrayList getCloseCer(){
//        ArrayList<Ticket>tickets=new ArrayList<>();
//
//
//        return tickets;
//    }
//}

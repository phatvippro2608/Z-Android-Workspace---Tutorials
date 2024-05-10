package com.example.knn2021.connection;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Connection {

    void readEvents(){

    }

    public boolean mCreateAndSaveFile(String params, String mJsonResponse, Context view) {
        try {
            FileWriter file = new FileWriter("/data/data/" +
                    view.getPackageName() + "/" + params);
            file.write(mJsonResponse);
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String mReadJsonData(String params, Context view) {
        try {
            File f = new File("/data/data/" + view.getPackageName() + "/" + params);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String mResponse = new String(buffer);
            return mResponse;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Error";
        }
    }
}

//
//    public boolean mCreateAndSaveFile(String params, String mJsonResponse, Context view) {
//        try {
//            FileWriter file = new FileWriter("/data/data/" +
//                    view.getPackageName() + "/" + params);
//            file.write(mJsonResponse);
//            file.flush();
//            file.close();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public String mReadJsonData(String params, Context view) {
//        try {
//            File f = new File("/data/data/" + view.getPackageName() + "/" + params);
//            FileInputStream is = new FileInputStream(f);
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            String mResponse = new String(buffer);
//            return mResponse;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return "Error";
//        }
//    }
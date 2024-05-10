package com.ntp.ontaplistview;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Connection {

    void readEvents(){

    }

    public void mCreateAndSaveFile(String params, String mJsonResponse, Context view) {
        try {
            FileWriter file = new FileWriter("/data/data/" +
                    view.getPackageName() + "/" + params);
            file.write(mJsonResponse);
            file.flush();
            file.close();
//            Toast.makeText(view.getApplicationContext(), mJsonResponse, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
//            Toast.makeText(view.getApplicationContext(), mJsonResponse, Toast.LENGTH_LONG).show();
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

package com.ntp.onthi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    Activity context;
    int resource;
    ArrayList<Sach> sachArrayList = new ArrayList<>();
    public SachAdapter(@NonNull Activity context, int resource, ArrayList<Sach> sachArrayList) {
        super(context, resource, sachArrayList);
        this.context = context;
        this.resource = resource;
        this.sachArrayList = sachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View customView = inflater.inflate(this.resource, null);
        TextView txtMauBia = customView.findViewById(R.id.txtMauBia);
        TextView txtMS = customView.findViewById(R.id.txtMS);
        TextView txtTS = customView.findViewById(R.id.txtTS);

        Sach sach = sachArrayList.get(position);
        if(sach.getMaubia()==0){
            txtMauBia.setBackgroundColor(Color.BLUE);
        }else if(sach.getMaubia()==1){
            txtMauBia.setBackgroundColor(Color.YELLOW);
        }
        txtMS.setText(sach.getMasach());
        txtTS.setText(sach.getTensach());
        return customView;
    }
}

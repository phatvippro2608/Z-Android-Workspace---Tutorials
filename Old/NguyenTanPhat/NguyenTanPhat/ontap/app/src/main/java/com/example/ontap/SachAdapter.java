package com.example.ontap;

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
    ArrayList<Sach> sachs;
    public SachAdapter(@NonNull Activity context, int resource,@NonNull ArrayList<Sach> sachs) {
        super(context, resource, sachs);
        this.context = context;
        this.resource = resource;
        this.sachs = sachs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        //View customView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        TextView txtMauBia = customView.findViewById(R.id.txtMauBia);
        TextView txtMaSach = customView.findViewById(R.id.txtMaSach);
        TextView txtTenSach = customView.findViewById(R.id.txtTenSach);
        Sach s = sachs.get(position);
        if(s.getMaubia()==0){
            txtMauBia.setBackgroundColor(Color.BLUE);
        }else if(s.getMaubia()==1){
            txtMauBia.setBackgroundColor(Color.YELLOW);
        }
        txtMaSach.setText(s.getMasach());
        txtTenSach.setText(s.getTensach());
        return customView;
    }
}

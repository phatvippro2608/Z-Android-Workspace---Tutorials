package com.ntp.ontapccbai3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    ArrayList<NhanVien> nhanViens = new ArrayList<>();
    public NhanVienAdapter(@NonNull Activity context, int resource, ArrayList<NhanVien>nhanViens) {
        super(context, resource, nhanViens);
        this.context = context;
        this.resource = resource;
        this.nhanViens = nhanViens;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        TextView txtMaNv = customView.findViewById(R.id.txtMaNv);
        TextView txtHoten = customView.findViewById(R.id.txtHoten);
        TextView txtGioitinh = customView.findViewById(R.id.txtGioitinh);
        TextView txtNoisinh = customView.findViewById(R.id.txtNoisinh);
        NhanVien nhanVien = nhanViens.get(position);
        txtMaNv.setText(nhanVien.getManv()+"");
        txtHoten.setText(nhanVien.getHoten());
        if(nhanVien.getGioitinh()==0)
            txtGioitinh.setText("Giới tính: Nam");
        else{
            txtGioitinh.setText("Giới tính: Nữ");
        }
        txtNoisinh.setText(nhanVien.getNoisinh());

        return customView;
    }
}

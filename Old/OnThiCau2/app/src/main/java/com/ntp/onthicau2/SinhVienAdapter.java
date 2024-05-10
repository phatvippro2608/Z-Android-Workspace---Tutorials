package com.ntp.onthicau2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Activity context;
    int resource;
    ArrayList<SinhVien> sinhViens;

    public SinhVienAdapter(@NonNull Activity context, int resource, ArrayList<SinhVien> sinhViens) {
        super(context, resource, sinhViens);
        this.context = context;
        this.resource = resource;
        this.sinhViens = sinhViens;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        TextView txtMaSV = customView.findViewById(R.id.txtMaSV);
        TextView txtTenSV = customView.findViewById(R.id.txtTenSV);
        TextView txtNgaySinh = customView.findViewById(R.id.txtNgaySinh);
        SinhVien sv = sinhViens.get(position);
        txtMaSV.setText(sv.getMasv()+"");
        txtTenSV.setText(sv.getTensv());
        txtNgaySinh.setText(sv.getNgaysinh());

        txtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity mainActivity = new MainActivity();
//                mainActivity.xoaSV2(sv);
//                remove(sv);
            }
        });
        return customView;
    }
}

package com.ntp.kiemtra1;

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
    ArrayList<NhanVien> nhanViens;
    public NhanVienAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<NhanVien> nhanViens) {
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

        TextView txtHoten = customView.findViewById(R.id.txthoten);
        TextView txtNS = customView.findViewById(R.id.txtNs);
        TextView txtGT = customView.findViewById(R.id.txtGT);


        NhanVien nv = nhanViens.get(position);
        txtHoten.setText(nv.getHoten());
        txtNS.setText(nv.getNgaysinh());
        if(nv.getGioitinh() == 0){
            txtGT.setText("Nam");
        }else{
            txtGT.setText("Nữ");
        }

        return customView;
    }
}

package com.tsnguyentanphat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.activity.R;
import com.tsnguyentanphat.model.SanPham;

import java.io.IOException;
import java.io.InputStream;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    View customView;
    public SanPhamAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        customView = this.context.getLayoutInflater().inflate(this.resource, null);

        ImageView imgHinh = customView.findViewById(R.id.imgHinh);
        ImageView imgHinh2 = customView.findViewById(R.id.imgHinh2);
        TextView txtName = customView.findViewById(R.id.txtName);
        TextView txtCost = customView.findViewById(R.id.txtCost);

        SanPham sanPham = getItem(position);
        imgHinh.setImageDrawable(sanPham.getD());
        txtName.setText(sanPham.getName());
        txtCost.setText(sanPham.getCost());

        return customView;
    }
}

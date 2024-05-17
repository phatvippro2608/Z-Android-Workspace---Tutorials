package com.example.laplichbaotri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterList_CreatePM extends ArrayAdapter<AssetName> {
    int layout;
    Context context;
    List<AssetName> list;
    public AdapterList_CreatePM(@NonNull Context context, int resource, @NonNull List<AssetName> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.list = objects;
    }
    public class ViewHolder{
        TextView textView;
        Button delete;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.TV_createpm);
            viewHolder.delete = convertView.findViewById(R.id.btnDelete_ItemCreatePM);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        AssetName assetName = list.get(position);
        viewHolder.textView.setText(assetName.getAssetName());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return  convertView;
    }
}

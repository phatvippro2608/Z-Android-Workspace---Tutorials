package com.example.laplichbaotri;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.SQLException;
import java.util.List;

import SQL.UserModel;

public class AdapterPMList extends ArrayAdapter<LICHBAOTRI> {
    int layout;
    Context context;
    List<LICHBAOTRI> list;
    public AdapterPMList(@NonNull Context context, int resource, @NonNull List<LICHBAOTRI> objects) {
        super(context, resource, objects);
        this.layout = resource;
        this.context = context;
        this.list = objects;
    }
    public class ViewHolder{
        ImageView imageView;
        TextView tv1,tv2,tv3;
        CheckBox checkBox;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.ImageView);
            viewHolder.tv1 = convertView.findViewById(R.id.TV1_pmList);
            viewHolder.tv2 = convertView.findViewById(R.id.tv2_PMList);
            viewHolder.tv3 = convertView.findViewById(R.id.tv3_PMlist);
            viewHolder.checkBox = convertView.findViewById(R.id.checkBox);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        LICHBAOTRI lichbaotri = list.get(position);
        viewHolder.tv1.setText(lichbaotri.getAssetName()+"**SN:"+lichbaotri.getAssetSN());
        viewHolder.tv2.setText(lichbaotri.getTaskName());
        viewHolder.tv3.setText(lichbaotri.getScheduleType());

        if (lichbaotri.getIsComplete()==1){
            viewHolder.checkBox.setChecked(true);
        }else{
            viewHolder.checkBox.setChecked(false);
        }

        try {
            byte[] photo = null;
            UserModel userModel = new UserModel();

            photo = userModel.getPhoto(list.get(position).getAssetID());

            Bitmap bitmap = BitmapFactory.decodeByteArray(photo,0,photo.length);
            viewHolder.imageView.setImageBitmap(bitmap);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  convertView;
    }
}

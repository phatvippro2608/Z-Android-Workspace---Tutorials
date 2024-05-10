package com.ntp.sqlite.adapter;

import static android.os.Build.VERSION_CODES.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ntp.sqlite.MainActivity;
import com.ntp.sqlite.R;
import com.ntp.sqlite.database.sqlite;
import com.ntp.sqlite.model.CongViec;
import com.ntp.sqlite.ui.NoteEditActivity;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private Activity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(Activity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }



    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNameOfJob;
        ImageView btnEditJob;
        ImageView btnDeleteJob;
        CongViec cv;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtNameOfJob = view.findViewById(com.ntp.sqlite.R.id.txt_nameOfJob);
            holder.btnEditJob = view.findViewById(com.ntp.sqlite.R.id.btn_editJob);
            holder.btnDeleteJob = view.findViewById(com.ntp.sqlite.R.id.btn_deleteJob);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        CongViec congViec = congViecList.get(position);
        holder.txtNameOfJob.setText(congViec.getTenCV());
        holder.btnEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cv",congViecList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnDeleteJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.DeleteNote(congViecList.get(position).getId());
                MainActivity mainActivity = new MainActivity();
                mainActivity.DeleteNote(congViecList.get(position).getId());
            }
        });
        return view;
    }
//
//    private void setEvents() {
//        btnEditJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), NoteEditActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("cv",cv);
//                intent.putExtras(bundle);
//                getContext().startActivity(intent);
//            }
//        });
//        btnDeleteJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("Comfire to Delete");
//                builder.setMessage("Are you sure to want delete this note?");
//                builder.setIcon(android.R.drawable.ic_dialog_info);
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        sqlite database = new sqlite(getContext(), "ghichu.sqlite", null, 1);
//                        String query = "DELETE FROM CongViec WHERE id = "+cv.getId();
//                        database.QueryData(query);
//                        context.StartAc();
//                    }
//                });
//                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//                builder.create().show();
//            }
//        });
//    }
}

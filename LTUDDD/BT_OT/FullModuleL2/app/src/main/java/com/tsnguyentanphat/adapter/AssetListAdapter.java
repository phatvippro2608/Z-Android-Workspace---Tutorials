package com.tsnguyentanphat.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.activity.R;
import com.tsnguyentanphat.model.Asset;
import com.tsnguyentanphat.model.AssetList;

public class AssetListAdapter extends ArrayAdapter<AssetList> {
    Activity context;
    int resource;
    public AssetListAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource,null);

        TextView txtAssetName = customView.findViewById(R.id.txtAssetName),
                txtAssetSN = customView.findViewById(R.id.txtAssetSN),
                txtDepartment = customView.findViewById(R.id.txtDepartment);
        ImageView imgDelete = customView.findViewById(R.id.imgDelete),
                imgTransfer = customView.findViewById(R.id.imgTransfer),
                imgEdit = customView.findViewById(R.id.imgEdit);

        AssetList assetList = getItem(position);
        txtAssetName.setText(assetList.getAssetName());
        txtDepartment.setText(assetList.getDepartment());
        txtAssetSN.setText(assetList.getAssetSN());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return customView;
    }

    private void deleteItem(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Confirm to delete");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove(getItem(position));
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        builder.show();
    }
}

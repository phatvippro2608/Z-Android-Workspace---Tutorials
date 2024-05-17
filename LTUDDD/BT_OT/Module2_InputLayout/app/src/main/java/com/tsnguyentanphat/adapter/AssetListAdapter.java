package com.tsnguyentanphat.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.activity.R;
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

        TextView txtAssetName = customView.findViewById(R.id.txtAsssetName);
        TextView txtDepartmentName = customView.findViewById(R.id.txtDepartmentName);
        TextView txtAssetSN = customView.findViewById(R.id.txtAssetSN);
        ImageView imgEdit = customView.findViewById(R.id.imgEdit);
        ImageView imgChange = customView.findViewById(R.id.imgChange);
        ImageView imgDelete = customView.findViewById(R.id.imgDelete);

        final AssetList assetList = getItem(position);
        txtAssetName.setText(assetList.getAssetName());
        txtDepartmentName.setText(assetList.getDepartmentName());
        txtAssetSN.setText(assetList.getAssetSN());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeletDialog(assetList);
            }
        });

        return customView;
    }

    private void openDeletDialog(AssetList assetList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("CONFIRM TO DELETE");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove(assetList);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

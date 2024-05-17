package com.tsnguyentanphat.nguyentanphat.adapter;

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

import com.tsnguyentanphat.nguyentanphat.R;
import com.tsnguyentanphat.nguyentanphat.activity.MainActivity;
import com.tsnguyentanphat.nguyentanphat.model.AssetList;

public class AssetListAdapter extends ArrayAdapter<AssetList> {
    Activity context;
    int resource;
    View customView;
    public AssetListAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        customView = this.context.getLayoutInflater().inflate(this.resource, null);

        TextView txtAssetName = customView.findViewById(R.id.txtAssetName);
        TextView txtDepartment = customView.findViewById(R.id.txtDepartment);
        TextView txtAssetSN = customView.findViewById(R.id.txtAssetSN);
        ImageView imgDelete = customView.findViewById(R.id.imgDelete);

        AssetList assetList = getItem(position);
        txtAssetName.setText(assetList.getAssetName());
        txtDepartment.setText(assetList.getDepartment());
        txtAssetSN.setText(assetList.getAssetSN());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(position);
            }
        });

        return customView;
    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Confirm to Delete");
        builder.setMessage("Are you sure you want to delete");
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove(getItem(position));
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

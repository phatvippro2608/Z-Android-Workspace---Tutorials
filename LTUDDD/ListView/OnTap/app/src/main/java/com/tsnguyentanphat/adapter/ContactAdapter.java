package com.tsnguyentanphat.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tsnguyentanphat.activity.MainActivity;
import com.tsnguyentanphat.activity.R;
import com.tsnguyentanphat.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    public ContactAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get Layout
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);
        //View customView = this.context.getLayoutInflater().inflate(this.resource,null);
        TextView txtName = customView.findViewById(R.id.txtName);
        TextView txtPhone = customView.findViewById(R.id.txtPhone);
        ImageView imgCall = customView.findViewById(R.id.imgCall);
        ImageView imgSMS = customView.findViewById(R.id.imgSMS);
        ImageView imgEdit = customView.findViewById(R.id.imgEdit);
        //Get add
        final Contact contact = getItem(position);
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog(contact);
            }
        });
        return customView;
    }
    private void openDeleteDialog(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm to Delete");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                remove(contact);
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

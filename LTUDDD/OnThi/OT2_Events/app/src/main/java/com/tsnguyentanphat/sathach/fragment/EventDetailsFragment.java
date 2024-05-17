package com.tsnguyentanphat.sathach.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.sathach.activity.R;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsFragment extends Fragment {
    TextView txtTitle, txtDescription;
    ImageView img1, img2, img3;
    int count;
    View view;
    String imgName1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_details, container, false);
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        ImageView imgView;
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog builder = new Dialog(getActivity());
                ImageView imgView = new ImageView(getActivity());
                imgView.setImageDrawable(getDrawable(imgName1));
                builder.addContentView(imgView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
                builder.show();
            }
        });
    }

    private void addControls() {
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);

        Bundle bundle = getArguments();
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        imgName1 = bundle.getString("imgName");

        img1.setImageDrawable(getDrawable(imgName1));
        txtTitle.setText(title);
        txtDescription.setText(description);
    }

    private Drawable getDrawable(String imgName1) {
        Drawable d;
        try {
            InputStream inputStream = getActivity().getAssets().open("image/"+imgName1);
            d = Drawable.createFromStream(inputStream,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return d;
    }
}
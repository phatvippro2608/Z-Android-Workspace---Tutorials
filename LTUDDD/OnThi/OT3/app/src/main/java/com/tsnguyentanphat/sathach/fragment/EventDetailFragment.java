package com.tsnguyentanphat.sathach.fragment;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsnguyentanphat.sathach.activity.R;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailFragment extends Fragment {
    View view;
    TextView txtTitle, txtDescription, txtCount;
    ImageView img1, img2, img3;
    Drawable img1D,img2D,img3D;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        bundle = getArguments();
        addControls();
        addEvents();
        return view;
    }

    private void addEvents() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog builder = new Dialog(getActivity());
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageDrawable(img1D);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        900,900
                ));
                builder.show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog builder = new Dialog(getActivity());
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageDrawable(img1D);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        900,900
                ));
                builder.show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog builder = new Dialog(getActivity());
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageDrawable(img1D);
                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        900,900
                ));
                builder.show();
            }
        });
    }

    private void addControls() {
        txtTitle = view.findViewById(R.id.txtTitle);
        txtDescription = view.findViewById(R.id.txtDescription);
        txtCount = view.findViewById(R.id.txtCount);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        txtTitle.setText(bundle.getString("title"));
        txtDescription.setText(bundle.getString("description"));
        txtCount.setText(bundle.getInt("count")+" view");
        img1D = getDrawable(bundle.getString("imgName"));
        img1.setImageDrawable(img1D);
        img2.setImageDrawable(getDrawable(bundle.getString("imgName")));
        img3.setImageDrawable(getDrawable(bundle.getString("imgName")));
    }

    private Drawable getDrawable(String imgName) {
        Drawable d;
        try {
            InputStream inputStream = getContext().getAssets().open("image/"+imgName);
            d = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return d;
    }
}
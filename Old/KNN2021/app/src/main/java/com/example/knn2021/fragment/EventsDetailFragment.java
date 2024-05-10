package com.example.knn2021.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.knn2021.R;
public class EventsDetailFragment extends Fragment {

    TextView NhanDL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_detail, container, false);
        NhanDL = view.findViewById(R.id.NhanDL);
        getParentFragmentManager().setFragmentResultListener("events", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                NhanDL.setText(result.getInt("count")+"");

            }
        });
        return view;
    }
}
package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigation.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ListView lvUser;
    ArrayAdapter adapter;
    String []arrData={"anh2","anh3","anh4","anh5","co2","co4"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        lvUser = view.findViewById(R.id.lvUser);
        adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrData
        );
        lvUser.setAdapter(adapter);


        return view;
    }
}
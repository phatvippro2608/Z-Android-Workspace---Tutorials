package com.tsNguyenTanPhat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tsNguyenTanPhat.dao.AssetGroupDAO;
import com.tsNguyenTanPhat.dao.AssetListDAO;
import com.tsNguyenTanPhat.model.AssetGroup;
import com.tsNguyenTanPhat.model.AssetList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvAssetList;
    Spinner spAssetGroup;
    ArrayAdapter<AssetList>assetListAdapter;
    ArrayAdapter<String>assetGroupAdapter;
    AssetListDAO assetListDAO = new AssetListDAO();
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        onClickSpinnerAssetGroup();

    }

    private void onClickSpinnerAssetGroup() {
        spAssetGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(assetGroupAdapter.getItem(position)!="-Asset Group-"){
                    String s = assetGroupAdapter.getItem(position);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    ArrayList<AssetList>assetListArray = assetListDAO.getAssetListByAG(s);
                    assetListAdapter.clear();
                    assetListAdapter.addAll(assetListArray);
                    //assetListAdapter.getFilter().filter(s);
                }
                else setAssetList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addControls() {
        setAssetList();
        setSpinnerAssetGroup();
    }

    private void setSpinnerAssetGroup() {
        spAssetGroup = findViewById(R.id.spAssetGroup);
        assetGroupAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item
        );
        spAssetGroup.setDropDownHorizontalOffset(android.R.layout.simple_dropdown_item_1line);
        spAssetGroup.setAdapter(assetGroupAdapter);
        ArrayList<AssetGroup>assetGroupList=new AssetGroupDAO().getAssetGroup();
        assetGroupAdapter.add("-Asset Group-");
        for (i=0; i<assetGroupList.size(); i++){
            assetGroupAdapter.addAll(assetGroupList.get(i).getName());
        }
    }

    private void setAssetList() {
        lvAssetList = findViewById(R.id.lvAssetList);
        assetListAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvAssetList.setAdapter(assetListAdapter);
        ArrayList<AssetList>assetListArray = assetListDAO.getAssetListBy();
        assetListAdapter.addAll(assetListArray);
    }
}
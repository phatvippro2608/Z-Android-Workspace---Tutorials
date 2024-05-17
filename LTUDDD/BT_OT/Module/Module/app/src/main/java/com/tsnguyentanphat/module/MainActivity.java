package com.tsnguyentanphat.module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tsnguyentanphat.dao.AssetListDAO;
import com.tsnguyentanphat.model.AssetList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvAssetList;
    ArrayAdapter<AssetList>assetListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAssetList = findViewById(R.id.lvAssetList);
        assetListAdapter = new ArrayAdapter<AssetList>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvAssetList.setAdapter(assetListAdapter);
        AssetListDAO assetListDAO = new AssetListDAO();
        ArrayList<AssetList>assetLists=assetListDAO.getAssetList();
        assetListAdapter.addAll(assetLists);
        lvAssetList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, AssetTransferActivity.class);
                AssetList a = new AssetList();
                a = assetListAdapter.getItem(i);
                String assetName = a.getAssetName();
                String departmentName = a.getDepartmentName();
                String assetSN = a.getAssetSN();
                intent.putExtra("assetName",assetName);
                intent.putExtra("departmentName",departmentName);
                intent.putExtra("assetSN",assetSN);
                startActivity(intent);
                return false;
            }
        });
    }
}
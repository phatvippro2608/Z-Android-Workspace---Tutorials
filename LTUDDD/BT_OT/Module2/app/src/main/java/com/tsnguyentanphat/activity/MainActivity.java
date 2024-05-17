package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tsnguyentanphat.dao.AssetDAO;
import com.tsnguyentanphat.model.Asset;
import com.tsnguyentanphat.model.AssetListView;
import com.tsnguyentanphat.model.Department;
import com.tsnguyentanphat.module2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvAssetList;
    Spinner spDepartment, spAssetGroup;
    String department="", assetGroup="";
    ImageView imgReset;

    ArrayAdapter<AssetListView>assetListViewAdapter;
    ArrayList<AssetListView>assetData = new ArrayList<>();
    AssetDAO assetDAO = new AssetDAO();

    ArrayAdapter<String>departmentSpinnerAdapter;
    ArrayList<String>departmentData = new ArrayList<>();

    ArrayAdapter<String>assetGroupSpinnerAdapter;
    ArrayList<String>assetGroupData = new ArrayList<>();
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        
    }

    private void addEvents() {
        spinnerEvents();
        buttonEvents();
    }

    private void buttonEvents() {
        imgReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                spDepartment.setId(0);
//                spAssetGroup.setId(0);
                refresh(1);
            }
        });

    }

    private void spinnerEvents() {
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = departmentSpinnerAdapter.getItem(position);
                if(department == "- Department -"){
                    department="";
                }
                //Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                refresh(caseValue(department, assetGroup));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAssetGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetGroup = assetGroupSpinnerAdapter.getItem(position);
                if(assetGroup=="- Asset Group -"){
                    assetGroup="";
                }
                refresh(caseValue(department,assetGroup));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addControls() {
        imgReset = findViewById(R.id.imgReset);
        accessAssetList();
        accessSpinnerDepartment();
        accessSpinnerAssetGroup();
        
    }

    private void accessSpinnerDepartment() {
        spDepartment = findViewById(R.id.spDepartment);
        departmentSpinnerAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item);
        spDepartment.setAdapter(departmentSpinnerAdapter);
        //spDepartment.setDropDownHorizontalOffset(android.R.layout.simple_list_item_checked);
        //spDepartment.setDropDownVerticalOffset(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinnerAdapter.add("- Department -");
        departmentData = assetDAO.getDepartment();
        departmentSpinnerAdapter.addAll(departmentData);

    }

    private void accessSpinnerAssetGroup() {
        spAssetGroup = findViewById(R.id.spAssetGroup);
        assetGroupSpinnerAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item);
        spAssetGroup.setAdapter(assetGroupSpinnerAdapter);
        //spDepartment.setDropDownHorizontalOffset(android.R.layout.simple_list_item_checked);
        //spDepartment.setDropDownVerticalOffset(android.R.layout.simple_spinner_dropdown_item);
        assetGroupSpinnerAdapter.add("- Asset Group -");
        assetGroupData = assetDAO.getAssetGroup();
        assetGroupSpinnerAdapter.addAll(assetGroupData);

    }

    private void accessAssetList() {
        lvAssetList = findViewById(R.id.lvAssetList);
        assetListViewAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvAssetList.setAdapter(assetListViewAdapter);
        assetData = assetDAO.getAsset();
        assetListViewAdapter.addAll(assetData);
    }

    public int caseValue(String department, String assetGroup){
        int value = 0;
        if (department=="" && assetGroup==""){
            value = 1;
        }else if(department!="" && assetGroup==""){
            value = 2;
        }else if(department=="" && assetGroup!=""){
            value = 3;
        }else if(department!="" && assetGroup!=""){
            value = 4;
        }

        return value;
    }
    public void refresh(int value){

        switch (value){
            case 1:
                assetData = assetDAO.getAsset();
                break;
            case 2:
                assetData = assetDAO.getAssetByD(department);
                break;
            case 3:
                assetData = assetDAO.getAssetByA(assetGroup);
                break;
            case 4:
                assetData = assetDAO.getAssetByDA(department,assetGroup);
                break;

        }

        assetListViewAdapter.clear();
        assetListViewAdapter.addAll(assetData);


    }
}

package com.tsnguyentanphat.nguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tsnguyentanphat.nguyentanphat.R;
import com.tsnguyentanphat.nguyentanphat.adapter.AssetListAdapter;
import com.tsnguyentanphat.nguyentanphat.dao.Module2DAO;
import com.tsnguyentanphat.nguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.nguyentanphat.model.AssetList;
import com.tsnguyentanphat.nguyentanphat.model.Department;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    Spinner spDepartment, spAssetGroup;
    TextInputEditText edtStartDate, edtEndDate, edtSearch;
    ListView lvAssetList;
    FloatingActionButton btnAddAsset;
    TextInputLayout layoutSearch;
    ArrayAdapter<String> departmentAdapter, assetGroupAdapter;
    AssetListAdapter assetListAdapter;
    Module2DAO module2DAO = new Module2DAO();
    String department="",assetGroup="",startDate="",endDate="",searchKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        clickDate();
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = departmentAdapter.getItem(position);
                if(department.equals("<Department>")){
                    department="";
                }
                filter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAssetGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetGroup=assetGroupAdapter.getItem(position);
                if(assetGroup.equals("<Asset Group>")){
                    assetGroup="";
                }
                filter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        layoutSearch.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchKey = edtSearch.getText().toString();
                if(searchKey!=""){
                    ArrayList<AssetList>assetLists= module2DAO.search(searchKey);
                    assetListAdapter.clear();
                    assetListAdapter.addAll(assetLists);
                }
            }
        });
        btnAddAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AssetInformationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filter() {
        ArrayList<AssetList>assetLists= module2DAO.filter(department,assetGroup,startDate,endDate);
        assetListAdapter.clear();
        assetListAdapter.addAll(assetLists);
    }

    private void clickDate() {
        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtStartDate.setText(dayOfMonth+"/"+month+1+"/"+year);
                        startDate=month+1+"/"+dayOfMonth+"/"+year;
                        filter();
                    }
                }, 2023, 2, 28);
                dialog.show();
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtEndDate.setText(dayOfMonth+"/"+month+1+"/"+year);
                        endDate=month+1+"/"+dayOfMonth+"/"+year;
                        filter();
                    }
                }, 2023, 2, 28);
                dialog.show();
            }
        });
    }

    private void addControls() {
        spDepartment = (Spinner) findViewById(R.id.spDepartment);
        spAssetGroup = (Spinner) findViewById(R.id.spAssetGroup);
        edtStartDate = (TextInputEditText) findViewById(R.id.edtStartDate);
        edtEndDate = (TextInputEditText) findViewById(R.id.edtEndDate);
        edtSearch = (TextInputEditText) findViewById(R.id.edtSearch);
        lvAssetList = (ListView) findViewById(R.id.lvAssetList);
        btnAddAsset = (FloatingActionButton) findViewById(R.id.btnAddAsset);
        layoutSearch = (TextInputLayout) findViewById(R.id.layoutSearch);
        setDepartment();
        setAssetGroup();
        setAssetList();
    }

    private void setAssetList() {
        ArrayList<AssetList>assetLists= module2DAO.getAssetList();
        assetListAdapter = new AssetListAdapter(
                MainActivity.this,
                R.layout.lv_asset_list_item
        );
        lvAssetList.setAdapter(assetListAdapter);
        assetListAdapter.addAll(assetLists);
    }

    private void setAssetGroup() {
        ArrayList<AssetGroup>assetGroups = module2DAO.getAssetGroup();
        assetGroupAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item
        );
        spAssetGroup.setAdapter(assetGroupAdapter);
        assetGroupAdapter.add("<Asset Group>");
        for(AssetGroup ag: assetGroups){
            assetGroupAdapter.add(ag.getName());
        }
    }

    private void setDepartment() {
        ArrayList<Department>departments=module2DAO.getDepartment();
        departmentAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item
        );
        spDepartment.setAdapter(departmentAdapter);
        departmentAdapter.add("<Department>");
        for(Department d:departments){
            departmentAdapter.add(d.getName());
        }
    }
}
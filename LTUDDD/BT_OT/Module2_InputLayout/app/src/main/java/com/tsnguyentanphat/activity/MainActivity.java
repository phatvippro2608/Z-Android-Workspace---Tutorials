package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tsnguyentanphat.adapter.AssetListAdapter;
import com.tsnguyentanphat.dao.AssetGroupDAO;
import com.tsnguyentanphat.dao.AssetListDAO;
import com.tsnguyentanphat.dao.DepartmentDAO;
import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.model.AssetList;
import com.tsnguyentanphat.model.Department;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int i;
    TextInputLayout layoutSearch;
    TextInputEditText edtSearch, edtStartDate, edtEndDate;
    AutoCompleteTextView actxtDepartment, actxtAssetGroup;
    ImageView imgResetData;
    ArrayAdapter<String> departmentAdapter, assetGroupAdapter;
    Calendar calendarStart = Calendar.getInstance();//Khong Can Lam
    Calendar calendarEnd = Calendar.getInstance();//Khong Can Lam
    ListView lvAssetList;
    AssetListAdapter assetListAdapter;
    String department="", assetGroup="", startDate="", endDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        actxtDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                department = actxtDepartment.getText().toString();
                if(department.equals("-Department-"))
                    department="";
                AssetListfilter(department,assetGroup,startDate,endDate);
            }
        });
        actxtAssetGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                assetGroup = actxtAssetGroup.getText().toString();
                if(assetGroup.equals("-Asset Group-"))
                    assetGroup="";
                AssetListfilter(department,assetGroup,startDate,endDate);
            }
        });
        layoutSearch.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Searching ...", Toast.LENGTH_SHORT).show();
                AssetListSearch();
            }
        });
        imgResetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actxtAssetGroup.clearListSelection();
                edtStartDate.setText("Start date");
                edtEndDate.setText("End date");
                edtSearch.setText("");
                edtSearch.setHint("Search");
                setAssetList();
            }
        });
    }

    private void addControls() {
        layoutSearch = findViewById(R.id.layoutSearch);
        edtSearch = findViewById(R.id.edtSearch);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtStartDate = findViewById(R.id.edtStartDate);
        imgResetData = findViewById(R.id.imgResetData);
        setDepartment();
        setAssetGroup();
        setAssetList();
        clickedDate();
    }


    private void AssetListSearch() {
        AssetListDAO assetListDAO = new AssetListDAO();
        ArrayList<AssetList>assetLists = assetListDAO.getAssetListSearch(edtSearch.getText().toString().trim());
        assetListAdapter.clear();
        for(i = 0; i<assetLists.size(); i++){
            assetListAdapter.add(assetLists.get(i));
        }
    }

    private void AssetListfilter(String department, String assetGroup, String startDate, String endDate) {
        AssetListDAO assetListDAO = new AssetListDAO();
        ArrayList <AssetList> assetLists = new ArrayList<>();
        assetLists = assetListDAO.getAssetListFilter(department,assetGroup,startDate,endDate);
        assetListAdapter.clear();
        for(i = 0; i<assetLists.size(); i++){
            assetListAdapter.add(assetLists.get(i));
        }
    }

    private void setAssetList() {
        lvAssetList = findViewById(R.id.lvAssetList);
        assetListAdapter = new AssetListAdapter(
                MainActivity.this,
                R.layout.asset_list_layout
        );
        lvAssetList.setAdapter(assetListAdapter);
        AssetListDAO assetListDAO = new AssetListDAO();
        ArrayList<AssetList>assetLists = assetListDAO.getAssetList();
        for(i=0; i<assetLists.size(); i++){
            assetListAdapter.add(assetLists.get(i));
        }
    }

    //StartDate, EndDate
    private void clickedDate(){
        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartDate();
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndDate();
            }
        });
    }
    private void openStartDate() {
        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarStart.set(Calendar.YEAR,year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edtStartDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                startDate = (month+1)+"/"+dayOfMonth+"/"+year;
                AssetListfilter(department,assetGroup,startDate,endDate);
            }
        }, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    private void openEndDate() {
        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarEnd.set(Calendar.YEAR,year);
                calendarEnd.set(Calendar.MONTH, month);
                calendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edtEndDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                endDate = (month+1)+"/"+dayOfMonth+"/"+year;
                AssetListfilter(department,assetGroup,startDate,endDate);
            }
        }, calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    //Department, AssetGroup
    private void setAssetGroup() {
        actxtAssetGroup = findViewById(R.id.actxtAssetGroup);
        assetGroupAdapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.dropdown_menu
        );
        actxtAssetGroup.setAdapter(assetGroupAdapter);
        AssetGroupDAO assetGroupDAO = new AssetGroupDAO();
        ArrayList<AssetGroup>assetGroupList = assetGroupDAO.getAssetGroup();
        assetGroupAdapter.add("-Asset Group-");
        for(i = 0; i<assetGroupList.size(); i++){
            assetGroupAdapter.add(assetGroupList.get(i).getAssetGroupName());
        }
    }

    private void setDepartment() {
        actxtDepartment = findViewById(R.id.actxtDepartment);
        departmentAdapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.dropdown_menu
        );
        actxtDepartment.setAdapter(departmentAdapter);
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<Department>departmentList = departmentDAO.getDepartment();
        departmentAdapter.add("-Department-");
        for (i=0; i<departmentList.size(); i++){
            departmentAdapter.add(departmentList.get(i).getDepartmentName());
        }
    }

}
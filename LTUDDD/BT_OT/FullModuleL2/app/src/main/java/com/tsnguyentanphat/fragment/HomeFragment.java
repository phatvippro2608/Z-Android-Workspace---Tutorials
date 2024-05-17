package com.tsnguyentanphat.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tsnguyentanphat.activity.InformationActivity;
import com.tsnguyentanphat.activity.R;
import com.tsnguyentanphat.adapter.AssetListAdapter;
import com.tsnguyentanphat.dao.AssetGroupDAO;
import com.tsnguyentanphat.dao.AssetListDAO;
import com.tsnguyentanphat.dao.DepartmentDAO;
import com.tsnguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.model.AssetList;
import com.tsnguyentanphat.model.Department;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    int i;
    Spinner  spDepartment, spAssetGroup;
    TextInputEditText edtStartDate, edtEndDate, edtSearch;
    TextInputLayout layoutSearch;
    ListView lvAssetList;
    //Department
    ArrayAdapter<String>departmentAdapter;
    DepartmentDAO departmentDAO = new DepartmentDAO();
    //AssetGroup
    ArrayAdapter<String>assetGroupAdapter;
    AssetGroupDAO assetGroupDAO = new AssetGroupDAO();
    //AssetList
    AssetListAdapter assetListAdapter;
    AssetListDAO assetListDAO = new AssetListDAO();
    //Filter
    String department="",assetGroup = "", startDate = "", endDate = "", searchKey = "";
    //AddAsset
    FloatingActionButton btnAdd;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        addControls();
        addEvents();
        return view;
    }

    public void addEvents(){
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = departmentAdapter.getItem(position);
                if(department.equals("<Department>"))
                    department = "";
                filter();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAssetGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetGroup = assetGroupAdapter.getItem(position);
                if(assetGroup.equals("<Asset Group>"))
                    assetGroup="";
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
                if(!searchKey.isEmpty()){
                    ArrayList<AssetList>assetLists=assetListDAO.searchAssetList(searchKey);
                    assetListAdapter.clear();
                    assetListAdapter.addAll(assetLists);
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void filter() {
        ArrayList<AssetList>assetLists = assetListDAO.filterAssetList(department,assetGroup,startDate,endDate);
        assetListAdapter.clear();
        assetListAdapter.addAll(assetLists);
    }

    public void addControls() {
        spDepartment = view.findViewById(R.id.spDepartment);
        spAssetGroup = view.findViewById(R.id.spAssetGroup);
        edtStartDate = view.findViewById(R.id.edtStartDate);
        edtEndDate = view.findViewById(R.id.edtEndDate);
        edtSearch = view.findViewById(R.id.edtSearch);
        layoutSearch = view.findViewById(R.id.layoutSearch);
        lvAssetList = view.findViewById(R.id.lvAssetList);
        btnAdd = view.findViewById(R.id.btnAdd);
        setDepartment();
        setAssetGroup();
        setWarrantyDate();
        setAssetList();
    }

    private void setDepartment() {
        departmentAdapter = new ArrayAdapter<>(
                getActivity(),
                //android.R.layout.simple_spinner_dropdown_item
                R.layout.spinner_item
        );
        spDepartment.setAdapter(departmentAdapter);
        departmentAdapter.add("<Department>");
        ArrayList<Department>departments = departmentDAO.getDepartment();
        for (i=0; i<departments.size(); i++ ){
            departmentAdapter.add(departments.get(i).getName());
        }
    }

    private void setAssetGroup(){
        assetGroupAdapter = new ArrayAdapter<>(
                getActivity(),
                //android.R.layout.simple_spinner_dropdown_item
                R.layout.spinner_item
        );
        spAssetGroup.setAdapter(assetGroupAdapter);
        assetGroupAdapter.add("<Asset Group>");
        ArrayList<AssetGroup>assetGroups=assetGroupDAO.getAssetGroup();
        for (i=0; i<assetGroups.size(); i++){
            assetGroupAdapter.add(assetGroups.get(i).getName());
        }
    }

    private void setWarrantyDate(){
        edtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtStartDate.setText(dayOfMonth+"/"+month+"/"+year);
                        startDate=month+"/"+dayOfMonth+"/"+year;
                        filter();
                    }
                }, 2023, 2, 23);
                dialog.show();
            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtEndDate.setText(dayOfMonth+"/"+month+"/"+year);
                        endDate=month+"/"+dayOfMonth+"/"+year;
                        filter();
                    }
                }, 2023, 2, 23);
                dialog.show();
            }
        });
    }

    private void setAssetList(){
        assetListAdapter = new AssetListAdapter(
                getActivity(),
                R.layout.asset_list_item
        );
        lvAssetList.setAdapter(assetListAdapter);
        ArrayList<AssetList>assetLists=assetListDAO.getAssetList();
        assetListAdapter.addAll(assetLists);
    }

}
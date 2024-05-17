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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.nguyentanphat.R;
import com.tsnguyentanphat.nguyentanphat.dao.Module2DAO;
import com.tsnguyentanphat.nguyentanphat.model.Asset;
import com.tsnguyentanphat.nguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.nguyentanphat.model.Department;
import com.tsnguyentanphat.nguyentanphat.model.Employee;
import com.tsnguyentanphat.nguyentanphat.model.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class AssetInformationActivity extends AppCompatActivity {
    TextView txtBack, txtAssetSN, txtSubmit, txtCancle;
    EditText edtAssetName, edtDescription, edtWarranty;
    Spinner spDepartment, spAssetGroup, spLocation, spAccountParty;
    Module2DAO module2DAO = new Module2DAO();
    ArrayAdapter<String>departmentAdapter, assetGroupAdapter, locationAdapter, accountPartyAdapter;
    String assetName="", deparment="",location="", assetGroup="", accountParty="", description="", warranty = "", assetSN="", dd="nn", gg="gg", nnnn="nnnn";
    ArrayList<Department>departments=module2DAO.getDepartment();
    ArrayList<AssetGroup>assetGroups=module2DAO.getAssetGroup();
    Calendar calendar = Calendar.getInstance();
    int yearNow = calendar.get(Calendar.YEAR), monthNow = calendar.get(Calendar.MONTH), dayNow = calendar.get(Calendar.DAY_OF_MONTH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_information);
        addControls();
        addEvents();
    }

    private void addEvents() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edtWarranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickWarranty();
            }
        });
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deparment=departmentAdapter.getItem(position);
                for(Department d:departments){
                    if(deparment.equals(d.getName())){
                        dd = "0"+d.getId();
                        if(!dd.equals(gg)){
                            nnnn = "0001";
                        }else{
                            nnnn = "0002";
                        }
                        setAssetSN(dd,gg,nnnn);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAssetGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetGroup = assetGroupAdapter.getItem(position);
                for(AssetGroup ag:assetGroups){
                    if(assetGroup.equals(ag.getName())){
                        gg = "0"+ag.getId();
                        if(!dd.equals(gg)){
                            nnnn = "0001";
                        }else{
                            nnnn = "0002";
                        }
                        setAssetSN(dd,gg,nnnn);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = spLocation.getSelectedItem().toString();
                assetName = edtAssetName.getText().toString();
                accountParty = spAccountParty.getSelectedItem().toString();
                description = edtDescription.getText().toString();
                warranty = edtWarranty.getText().toString();
                assetSN = txtAssetSN.getText().toString();
                if(assetName!="" && deparment!="" && location!="" && assetGroup!="" && accountParty!=""){
                    module2DAO.addAsset(assetName, deparment,location, assetGroup, accountParty, description, warranty , assetSN);
                    Toast.makeText(AssetInformationActivity.this, "Success to add Asset", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AssetInformationActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AssetInformationActivity.this, "Hãy chắc rằng thông tin điền đầy đủ và chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setAssetSN(String dd, String gg, String nnnn) {
        txtAssetSN.setText(dd+"/"+gg+"/"+nnnn);
    }

    private void clickWarranty() {
        DatePickerDialog dialog = new DatePickerDialog(AssetInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Boolean flag = false;
                if(year<=yearNow){
                    if(month<=monthNow){
                        if(dayOfMonth<=dayNow){
                            edtWarranty.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            flag = true;
                        }
                    }
                }
                if(flag == false){
                    edtWarranty.setText("Error Date");
                    Toast.makeText(AssetInformationActivity.this, "Warranty phải nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                }
            }
        }, yearNow, monthNow, dayNow);
        dialog.show();
    }

    private void addControls() {
        txtBack = findViewById(R.id.txtBack);
        txtAssetSN = findViewById(R.id.txtAssetSN);
        edtAssetName = findViewById(R.id.edtAssetName);
        edtDescription = findViewById(R.id.edtDescription);
        edtWarranty = findViewById(R.id.edtWarranty);
        spDepartment = findViewById(R.id.spDepartment);
        spAssetGroup = findViewById(R.id.spAssetGroup);
        spLocation = findViewById(R.id.spLocation);
        spAccountParty = findViewById(R.id.spAccountParty);
        txtSubmit = findViewById(R.id.txtSubmit);
        txtCancle = findViewById(R.id.txtCancel);
        setDepartment();
        setAssetGroup();
        setLocation();
        setAccountParty();
    }

    private void setAccountParty() {
        accountPartyAdapter = new ArrayAdapter<>(
                AssetInformationActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spAccountParty.setAdapter(accountPartyAdapter);
        accountPartyAdapter.add("<Accountable Party>");
        ArrayList<Employee>employees=module2DAO.getEmployee();
        for(Employee e:employees){
            accountPartyAdapter.add(e.getLastName());
        }
    }

    private void setLocation() {
        locationAdapter = new ArrayAdapter<>(
                AssetInformationActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spLocation.setAdapter(locationAdapter);
        locationAdapter.add("<Location>");
        ArrayList<Location>locations=module2DAO.getLocation();
        for(Location l:locations){
            locationAdapter.add(l.getName());
        }
    }

    private void setAssetGroup() {
        assetGroupAdapter = new ArrayAdapter<>(
                AssetInformationActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spAssetGroup.setAdapter(assetGroupAdapter);
        assetGroupAdapter.add("<Asset Group>");

        for(AssetGroup ag:assetGroups){
            assetGroupAdapter.add(ag.getName());
        }
    }

    private void setDepartment() {
        departmentAdapter = new ArrayAdapter<>(
                AssetInformationActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spDepartment.setAdapter(departmentAdapter);
        departmentAdapter.add("<Department>");
        for(Department d:departments){
            departmentAdapter.add(d.getName());
        }
    }

}
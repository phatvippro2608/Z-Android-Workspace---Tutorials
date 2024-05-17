package com.tsnguyentanphat.module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.dao.AssetDAO;
import com.tsnguyentanphat.dao.DistinationDepartmentDAO;
import com.tsnguyentanphat.model.Asset;
import com.tsnguyentanphat.model.Department;
import com.tsnguyentanphat.model.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AssetTransferActivity extends AppCompatActivity {
    int i;
    TextView txtSubmit, txtCancle;
    Spinner spAssetName, spDepartment, spLocation;
    EditText  edtCDepartment, edtAssetSN, edtNewAssetSN;
    ArrayAdapter<String>departmentAdapter;
    ArrayAdapter<String>locationAdapter;
    ArrayAdapter<String>assetNameAdapter;
    DistinationDepartmentDAO distinationDepartmentDAO = new DistinationDepartmentDAO();
    AssetDAO assetDAO = new AssetDAO();
    ArrayList<Asset>assets= new ArrayList<>();
    Asset assetTransfer = new Asset();
    String dd = "??", gg, nnnn = "????", assetName, departmentName, assetSN, newDepartment="", newLocation="", newAssetSN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_transfer);
        addControls();
        addEvents();
    }
    private void addEvents() {
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String departmentName = departmentAdapter.getItem(position);
                Toast.makeText(AssetTransferActivity.this, departmentName, Toast.LENGTH_SHORT).show();
                ArrayList<Department>departmentArrayList=distinationDepartmentDAO.getDepartment();
                for(i = 0; i<departmentArrayList.size(); i++){
                    if(departmentArrayList.get(i).getDepartmentName().equals(departmentName)){
                        newDepartment=departmentName;
                        dd = "0"+departmentArrayList.get(i).getId();
                        if(departmentArrayList.get(i).getId()!=assetTransfer.getAssetGroupID()){
                            nnnn = "0001";
                        }
                        else{
                            nnnn = "0002";
                        }
                        setNewAssetSN();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String location = locationAdapter.getItem(position);
                Toast.makeText(AssetTransferActivity.this, location, Toast.LENGTH_SHORT).show();
                ArrayList<Location>locations=distinationDepartmentDAO.getLocation();
                for(i = 0; i<locations.size(); i++){
                    if (locations.get(i).getLocationName().equals(location)){
                        newLocation = location;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newDepartment=="" || newLocation==""){
                    Toast.makeText(AssetTransferActivity.this, newDepartment+newLocation, Toast.LENGTH_SHORT).show();
                }
                else{
                    distinationDepartmentDAO.setTransferAsset(newDepartment,newLocation,newAssetSN,assetTransfer.getAssetName());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //Toast.makeText(AssetTransferActivity.this, newDepartment+newLocation, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void addControls() {
        spAssetName = findViewById(R.id.spAssetName);
        spDepartment = findViewById(R.id.spDepartment);
        spLocation = findViewById(R.id.spLocation);
        edtCDepartment = findViewById(R.id.edtCDapartment);
        edtAssetSN = findViewById(R.id.edtAssetSN);
        edtNewAssetSN = findViewById(R.id.edtNewAssetSN);
        txtSubmit = findViewById(R.id.txtSubmit);
        txtCancle = findViewById(R.id.txtCancle);
        //getIntent
        Intent intent = getIntent();
        assetName = intent.getStringExtra("assetName");
        departmentName = intent.getStringExtra("departmentName");
        assetSN = intent.getStringExtra("assetSN");
        assets = assetDAO.getAsset();
        assetNameAdapter = new ArrayAdapter<>(
                AssetTransferActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spAssetName.setAdapter(assetNameAdapter);
        assetNameAdapter.add(assetName);
        edtCDepartment.setText(departmentName);
        edtAssetSN.setText(assetSN);
        setNewDepartment();
        setNewLocation();
        setNewAssetSN();
        getObjectTransfer();
    }

    private Asset getObjectTransfer() {
        for(i = 0; i<assets.size(); i++){
            if (assets.get(i).getAssetName().equals(assetName)){
                assetTransfer = assets.get(i);
                break;
            }
        }
        return assetTransfer;
    }

    private void setNewAssetSN() {
        String oldAssetSN = edtAssetSN.getText().toString();
        gg = oldAssetSN.substring(3,5);
        newAssetSN=dd+"/"+gg+"/"+nnnn;
        edtNewAssetSN.setText(newAssetSN);
    }


    private void setNewLocation() {
        locationAdapter = new ArrayAdapter<>(
                AssetTransferActivity.this,
                android.R.layout.simple_spinner_dropdown_item
        );
        spLocation.setAdapter(locationAdapter);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        ArrayList<Location>locations = distinationDepartmentDAO.getLocation();
        locationAdapter.add("Location");
        for(i = 0; i<locations.size(); i++){
            locationAdapter.add(locations.get(i).getLocationName());
        }
    }

    private void setNewDepartment() {        departmentAdapter = new ArrayAdapter<>(
            AssetTransferActivity.this,
            android.R.layout.simple_spinner_dropdown_item
    );
        spDepartment.setAdapter(departmentAdapter);
        ArrayList<Department>departments = distinationDepartmentDAO.getDepartment();
        departmentAdapter.add("Department");
        for(i = 0; i<departments.size(); i++){
            departmentAdapter.add(departments.get(i).getDepartmentName());
        }
    }
}
package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.dao.AssetGroupDAO;
import com.tsnguyentanphat.dao.DepartmentDAO;
import com.tsnguyentanphat.dao.LocationDAO;
import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.model.Department;
import com.tsnguyentanphat.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity {
    int i;
    TextView txtActionName, txtBack;
    Spinner spDepartment, spAssetGroup, spLocation, spAccount;
    EditText edtAssetName, edtDescription, edtWarranty;
    TextView txtAssetSNValue;
    Button btnSubmit, btnCancle;
    DepartmentDAO departmentDAO = new DepartmentDAO();
    ArrayAdapter<String> departmentAdapter;
    AssetGroupDAO assetGroupDAO = new AssetGroupDAO();
    ArrayAdapter<String>assetGroupAdapter;
    LocationDAO locationDAO = new LocationDAO();
    ArrayAdapter<String>locationAdapter;
    ArrayAdapter<String>accountAdapter;
    String assetName="",department="",location="",assetGroup="",account="",description="",warranty="",assetSN="01/01/0001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
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
        setWarranty();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assetName=edtAssetName.getText().toString();
                department = spDepartment.getSelectedItem().toString();
                location = spLocation.getSelectedItem().toString();
                assetGroup = spAssetGroup.getSelectedItem().toString();
                account = spAccount.getSelectedItem().toString();
                description = edtDescription.getText().toString();
                String query = "insert into Assets\n" +
                        "values \n" +
                        "(?,?,\n" +
                        "(select top 1 ID from DepartmentLocations order by ID desc),\n" +
                        "(select ID from Employees where LastName = ?),\n" +
                        "(select ID from AssetGroups where Name = ?),\n" +
                        "?,?)";
                ConnectSQL connectSQL = new ConnectSQL();
                Connection connection = connectSQL.getConnection();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,assetSN);
                    preparedStatement.setString(2,assetName);
                    preparedStatement.setString(3,account);
                    preparedStatement.setString(4,assetGroup);
                    preparedStatement.setString(5,description);
                    preparedStatement.setString(6,warranty);
                    preparedStatement.execute();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        txtActionName = findViewById(R.id.txtActionName);
        txtBack = findViewById(R.id.txtBack);
        txtActionName.setText("Asset Information");
        spDepartment = findViewById(R.id.spDepartment);
        spAssetGroup = findViewById(R.id.spAssetGroup);
        edtAssetName = findViewById(R.id.edtAssetName);
        edtDescription = findViewById(R.id.edtDescription);
        edtWarranty = findViewById(R.id.edtWarranty);
        txtAssetSNValue = findViewById(R.id.txtAssetSNValue);
        spLocation = findViewById(R.id.spLocation);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancle = findViewById(R.id.btnCancle);
        spAccount = findViewById(R.id.spAccount);
        setDepartment();
        setAssetGroup();
        setLocation();
        setAccount();
    }

    private void setAccount() {
        accountAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.spinner_item_informationasset
        );
        spAccount.setAdapter(accountAdapter);
        accountAdapter.add("Valdivia");
    }

    private void setWarranty() {
        edtWarranty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(InformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtWarranty.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        warranty = month+"/"+dayOfMonth+"/"+year;
                    }
                }, 2023, 2, 25);
                dialog.show();
            }
        });
    }

    private void setLocation() {
        locationAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.spinner_item_informationasset
        );
        spLocation.setAdapter(locationAdapter);
        locationAdapter.add("<Location>");
        ArrayList<Location>locations=locationDAO.getLocation();
        for(i = 0; i<locations.size(); i++){
            locationAdapter.add(locations.get(i).getName());
        }
    }

    private void setDepartment() {
        departmentAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.spinner_item_informationasset
        );
        spDepartment.setAdapter(departmentAdapter);
        departmentAdapter.add("<Department>");
        ArrayList<Department>departments=departmentDAO.getDepartment();
        for(i = 0; i < departments.size(); i++ ){
            departmentAdapter.add(departments.get(i).getName());
        }
    }

    private void setAssetGroup(){
        assetGroupAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.spinner_item_informationasset
        );
        spAssetGroup.setAdapter(assetGroupAdapter);
        assetGroupAdapter.add("<Asset Group>");
        ArrayList<AssetGroup>assetGroups=assetGroupDAO.getAssetGroup();
        for(i=0; i<assetGroups.size(); i++){
            assetGroupAdapter.add(assetGroups.get(i).getName());
        }
    }
}
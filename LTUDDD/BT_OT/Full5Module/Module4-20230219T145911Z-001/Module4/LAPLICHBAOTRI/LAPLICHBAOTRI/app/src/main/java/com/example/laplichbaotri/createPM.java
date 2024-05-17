package com.example.laplichbaotri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SQL.UserModel;

public class createPM extends AppCompatActivity {
    Spinner TaskName,AssetName,Schedule;
    ListView ListView;
    Button AddList,Submit,Cancel;
    EditText Start,End,CurentOdo;

    UserModel userModel;

    List<SelectTask> selectTaskList;
    List<AssetName> assetNameList_Spinner; //for Spinner
    List<ScheduleModel> scheduleModels = new ArrayList<>();
    List<AssetName> listAsset = new ArrayList<>(); //for ListView

    AssetName asset_btnAdd;

    String SchecduleModel_for_btnSubmit;
    int TaskName_for_btnSubmit=0;
    int temp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        TaskName_Spinner();
        AssetName_Spinner();
        ScheduleModel_Spinner();

        //add to list
        AddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asset_btnAdd == null){
                    Toast.makeText(createPM.this,"Chưa chọn Asset",Toast.LENGTH_SHORT).show();
                }else{
                    if(!listAsset.isEmpty()){
                        int temp =0;
                        for (int i = 0; i < listAsset.size(); i++) {
                            if (asset_btnAdd.getID() == listAsset.get(i).getID()) {
                                temp = 1;
                            }
                        }
                        if (temp ==0){
                            listAsset.add(asset_btnAdd);
                            LOAD_ListView(listAsset);
                        }else{
                            Toast.makeText(createPM.this,"Asset đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        listAsset.add(asset_btnAdd);
                        LOAD_ListView(listAsset);
                    }
                }
            }
        });

        AssetName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    asset_btnAdd = null;
                }else{
                    asset_btnAdd = assetNameList_Spinner.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TaskName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    TaskName_for_btnSubmit = 0;
                }else{
                    TaskName_for_btnSubmit = selectTaskList.get(position).getID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Schedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    SchecduleModel_for_btnSubmit = "";
                    CurentOdo.setVisibility(View.GONE);
                    temp =0;
                }else{
                    if (position==1){
                        SchecduleModel_for_btnSubmit = "Monthly";
                        CurentOdo.setVisibility(View.GONE);
                        temp =1;
                    }else{
                        CurentOdo.setVisibility(View.VISIBLE);
                        temp =2;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Date
        Start.setOnClickListener(new View.OnClickListener() {
            Date date = new Date();
            int y = 2021;
            int m = 8;
            int d = 26;
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Start.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(createPM.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,y,m,d);
                datePickerDialog.show();

            }
        });

        End.setOnClickListener(new View.OnClickListener() {
            Date date = new Date();
            int y = 2021;
            int m = 8;
            int d = 26;
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        End.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(createPM.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,y,m,d);
                datePickerDialog.show();

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (End.getText().toString().isEmpty()||Start.getText().toString().isEmpty()||listAsset.isEmpty()||TaskName_for_btnSubmit == 0||temp == 0 || (temp ==2 && CurentOdo.getText().toString().isEmpty())){
                    Toast.makeText(createPM.this,"Chọn đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    if (SchecduleModel_for_btnSubmit==""){
                        SchecduleModel_for_btnSubmit = CurentOdo.getText().toString();
                    }
                    for (int i=0;i<listAsset.size();i++){
                        try {
                            boolean check = false;
                            check = userModel.Add_LongMaintenances(listAsset.get(i).getID(),TaskName_for_btnSubmit,SchecduleModel_for_btnSubmit,Start.getText().toString(),End.getText().toString());
                            if (check == true){
                                Toast.makeText(createPM.this,"Đã thêm "+listAsset.size()+" bảo trì",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(createPM.this,"Lỗi kết nối",Toast.LENGTH_SHORT).show();

                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //end onCreate
    }

    private void LOAD_ListView(List<AssetName> listAsset) {
        AdapterList_CreatePM assetNameAdapterList_createPM = new AdapterList_CreatePM(this,R.layout.item_createpm,listAsset);
        ListView.setAdapter(assetNameAdapterList_createPM);
     }

    private void ScheduleModel_Spinner() {
        scheduleModels.add(new ScheduleModel(0,"-Schedule Models-"));
        scheduleModels.add(new ScheduleModel(1,"Mothly"));
        scheduleModels.add(new ScheduleModel(2,"Milage"));
        ArrayAdapter<ScheduleModel> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,scheduleModels);
        Schedule.setAdapter(arrayAdapter);
    }

    private void AssetName_Spinner() {
        try {
            userModel = new UserModel();
            assetNameList_Spinner = userModel.AssetName();
            ArrayAdapter<AssetName> assetNameArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,assetNameList_Spinner);
            AssetName.setAdapter(assetNameArrayAdapter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void TaskName_Spinner() {
        try {
            userModel = new UserModel();
            selectTaskList = userModel.TaskName();
            ArrayAdapter<SelectTask> selectTaskArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,selectTaskList);
            TaskName.setAdapter(selectTaskArrayAdapter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void AnhXa() {
        TaskName = (Spinner)findViewById(R.id.SPTaskName);
        AssetName = (Spinner)findViewById(R.id.SPAssetName_CreatePM);
        Schedule = (Spinner)findViewById(R.id.SP_ScheduleModels);
        ListView = (ListView)findViewById(R.id.ListView_CreatePM);
        Submit = (Button)findViewById(R.id.btn_Submit);
        AddList = (Button)findViewById(R.id.btnAddtoList);
        Cancel = (Button)findViewById(R.id.btnCancel);
        Start = (EditText)findViewById(R.id.DateStart);
        End = (EditText)findViewById(R.id.DateEnd);
        CurentOdo = (EditText)findViewById(R.id.edtMilage);
        CurentOdo.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  R.id.btnBACK_itemActionbar:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
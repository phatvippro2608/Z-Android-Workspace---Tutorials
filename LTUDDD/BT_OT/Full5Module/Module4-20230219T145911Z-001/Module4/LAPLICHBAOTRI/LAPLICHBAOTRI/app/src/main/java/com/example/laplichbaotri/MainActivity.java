package com.example.laplichbaotri;

import androidx.annotation.Nullable;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import SQL.UserModel;



public class MainActivity extends AppCompatActivity {
    EditText ActiveDate;
    android.widget.ListView ListView;
    Spinner AssetName,SelectTaskl;
    Button Clear;
    FloatingActionButton floatingActionButton;

    UserModel userModel;

    List<LICHBAOTRI> lichbaotriList;
    List<AssetName> assetNameList;
    List<SelectTask> selectTaskList;

    AdapterPMList adapterPMList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pm);


        AnhXa();

        ActiveDate_now();


        AssetName_Spinner();

        SelectTask_Spinner();

        //ListView

        try {
            userModel = new UserModel();
            lichbaotriList = userModel.PMLIST();
            LOAD_LISTVIEW(lichbaotriList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //clear
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetName_Spinner();
                SelectTask_Spinner();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,createPM.class);
                startActivityForResult(intent,123);
            }
        });

        //ActiveDate
        ActiveDate.setOnClickListener(new View.OnClickListener() {
            Date date = new Date();
            int y = 2021;
            int m = 8;
            int d = 26;
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ActiveDate.setText(year+"-"+month+"-"+dayOfMonth);
                        try {
                            userModel =new UserModel();
                            lichbaotriList.clear();
                            lichbaotriList = userModel.Filter_ActiveDate(ActiveDate.getText().toString());
                            LOAD_LISTVIEW(lichbaotriList);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,dateSetListener,y,m,d);
                datePickerDialog.show();


            }
        });


        //FIlTER
        AssetName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){

                    try {
                        userModel = new UserModel();
                        lichbaotriList = userModel.PMLIST();
                        LOAD_LISTVIEW(lichbaotriList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }else{
                    LOAD_FILTER(assetNameList.get(position).getID());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SelectTaskl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    try {
                        userModel = new UserModel();
                        lichbaotriList = userModel.PMLIST();
                        LOAD_LISTVIEW(lichbaotriList);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }else{
                    LOAD_FILTER(selectTaskList.get(position).getID());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //end onCreate
    }

    private void ActiveDate_now() {
        Date date = new Date();
        String datenow = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        ActiveDate.setText(datenow);
    }

    @Override
    @Nullable
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            userModel = new UserModel();
            lichbaotriList.clear();
            lichbaotriList= userModel.PMLIST();
            LOAD_LISTVIEW(lichbaotriList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void LOAD_FILTER(int filter) {
        try {
            lichbaotriList.clear();
            userModel = new UserModel();
            lichbaotriList = userModel.FILTER_PMLIST(filter);
            LOAD_LISTVIEW(lichbaotriList);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void AnhXa() {
        ActiveDate = (EditText) findViewById(R.id.edtActiveDate);
        ListView = (ListView)findViewById(R.id.ListView_PMList);
        AssetName = (Spinner)findViewById(R.id.SP_AssetName_PMList);
        SelectTaskl = (Spinner)findViewById(R.id.SPSelectTask);
        Clear = (Button)findViewById(R.id.btn_Clearfilter);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingAction);
    }


    private void SelectTask_Spinner() {
        try {
            userModel = new UserModel();
            selectTaskList = userModel.TaskName();
            ArrayAdapter<SelectTask> selectTaskArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,selectTaskList);
            SelectTaskl.setAdapter(selectTaskArrayAdapter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void AssetName_Spinner() {
        try {
            userModel = new UserModel();
            assetNameList = userModel.AssetName();
            ArrayAdapter<AssetName> assetNameArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,assetNameList);
            AssetName.setAdapter(assetNameArrayAdapter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    private void LOAD_LISTVIEW(List<LICHBAOTRI> lichbaotris){

            adapterPMList = new AdapterPMList(MainActivity.this,R.layout.item_pmlist,lichbaotris);
            ListView.setAdapter(adapterPMList);
    }
}
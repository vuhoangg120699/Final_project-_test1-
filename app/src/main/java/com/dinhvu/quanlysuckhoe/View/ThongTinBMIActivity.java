package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.Adapter.BMIAdapter;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.BMI;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class ThongTinBMIActivity  extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView listView;
    private ArrayList<BMI>arrayList;
    private BMIAdapter adapter;
    private BMI bmi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_bmi);
        InitWidget();
        Init();
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin BMI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharePrefeConfig sharePrefeConfig=new SharePrefeConfig(this);
        bmi=new BMI(this);
        arrayList=bmi.getDataList(sharePrefeConfig.getSDT());
        adapter=new BMIAdapter(this,arrayList);
        listView.setAdapter(adapter);
    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        listView=findViewById(R.id.gv);
    }
}

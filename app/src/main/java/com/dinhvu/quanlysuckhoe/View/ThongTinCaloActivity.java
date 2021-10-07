package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.Adapter.CaloAdapter;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.Calo;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class ThongTinCaloActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView listView;
    private ArrayList<Calo>arrayList;
    private CaloAdapter adapter;
    private Calo calo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_bmi);
        InitWidget();
        Init();
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông Tin Calo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       SharePrefeConfig sharePrefeConfig=new SharePrefeConfig(this);
        calo=new Calo(this);
        arrayList=calo.getDataListCaLo(sharePrefeConfig.getSDT());
        adapter=new CaloAdapter(this,arrayList);
        listView.setAdapter(adapter);
    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        listView=findViewById(R.id.gv);
    }
}

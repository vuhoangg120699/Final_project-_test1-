package com.dinhvu.quanlysuckhoe.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.Calo;
import com.dinhvu.quanlysuckhoe.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IndexCaloActivity extends AppCompatActivity
implements View.OnClickListener {
    private Calo calo;
    private EditText editTuoi;
    private TextView txtKetQua,txtgioitinh,txtMucdo,editWeight,editHeight;
    private Button btnluu,btntinh;
    private Toolbar toolbar;

    private String sdt;
    private int checked=0;
    private SharePrefeConfig sharePrefeConfig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexcalo);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
         txtMucdo.setOnClickListener(this::onClick);
         txtgioitinh.setOnClickListener(this::onClick);
         btnluu.setOnClickListener(this::onClick);
         btntinh.setOnClickListener(this::onClick);

    }

    private void Init() {


       sharePrefeConfig=new SharePrefeConfig(this);
       sharePrefeConfig.PutshareFer(this);
       sdt=sharePrefeConfig.getSDT();
             if(sharePrefeConfig.getAge()>0){
                 editTuoi.setText(sharePrefeConfig.getAge()+"");
             }
        editHeight.setText(sharePrefeConfig.getHeight()+"");
        editWeight.setText(sharePrefeConfig.getWeight()+"");
        txtgioitinh.setText(sharePrefeConfig.getSex());
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tính Lượng Ca Lo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        calo=new Calo(this);

    }



    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        editHeight=findViewById(R.id.editchieucao);
        editWeight=findViewById(R.id.editcannang);

        txtKetQua=findViewById(R.id.txtketqua);
        btntinh=findViewById(R.id.btntinh);
        btnluu=findViewById(R.id.btnluu);
        txtgioitinh=findViewById(R.id.txtgioitinh);
        editTuoi=findViewById(R.id.edittuoi);
        txtMucdo=findViewById(R.id.txtmuchoatdong);


    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btntinh:
                  HandleCalo();break;
              case R.id.btnluu:
                  HandleSaveCaLo();break;
              case R.id.txtmuchoatdong:
                  DiaLogGioitinh(2);break;
              case R.id.txtgioitinh:
                  DiaLogGioitinh(1);break;


          }

    }

    private void HandleSaveCaLo() {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(checked!=0){
            String gioitinh=txtgioitinh.getText().toString().trim();
            int height= Integer.parseInt(editHeight.getText().toString().trim());
            int weight= Integer.parseInt(editWeight.getText().toString().trim());
            int tuoi= Integer.parseInt(editTuoi.getText().toString().trim());
            sharePrefeConfig.putSex(gioitinh);
            sharePrefeConfig.PutAge(tuoi);
            calo.AddCaLo(sharePrefeConfig.getSDT(),height,weight,gioitinh,tuoi,txtKetQua.getText().toString().trim(),simpleDateFormat.format(calendar.getTime()));
            Toast.makeText(this, "Đã Thêm", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Bạn phải tính toán !", Toast.LENGTH_SHORT).show();
        }

    }

    private void HandleCalo() {

        String gioitinh=txtgioitinh.getText().toString().trim();
        String muchoatdong=txtMucdo.getText().toString().trim();

        double BMR=0;
        double TDEE=2f;
        double R=0;
        if(gioitinh.length()>0 && editWeight.getText().toString().length()>0
        && editTuoi.getText().toString().length()>0 && editWeight.getText().toString().length()>0
          && muchoatdong.length()>0) {
            int height= Integer.parseInt(editHeight.getText().toString().trim());
            int weight= Integer.parseInt(editWeight.getText().toString().trim());
            int tuoi= Integer.parseInt(editTuoi.getText().toString().trim());
            if(height>0 && weight>0 && tuoi>0){
                switch (gioitinh){
                    case "Nam": BMR=(13.397*weight)+(4.799*height)+(5.677*tuoi)+88.362;break;
                    case "Nữ": BMR=(9.247*weight)+(3.098*height)+(4.330*tuoi)+447.593;break;
                }

                switch (muchoatdong){
                    case "Ít": R=1.2;break;
                    case "Nhẹ": R=1.375;break;
                    case "Vừa": R=1.55;break;
                    case "Nặng": R=1.725;break;
                    case "Rất Nặng": R=1.9;break;
                }
                TDEE=BMR*R;
                txtKetQua.setText(String.format("%.2f",TDEE)+" Calo");
                checked=1;
            }else{
                Toast.makeText(this, "#0", Toast.LENGTH_SHORT).show();
            }
            
        }else{
            Toast.makeText(this, "Không để trống bất kỳ thông tin nào !", Toast.LENGTH_SHORT).show();
        }
            
        
    }

    private void DiaLogGioitinh(int i) {
        String[] gioitinhs={"Nam","Nữ"};
        String[] Mucdos={"Ít","Nhẹ","Vừa","Nặng","Rất Nặng"};
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_nhap);
        dialog.show();
        AppCompatSpinner spinner=dialog.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter=null;
        switch (i){
            case 1: arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,gioitinhs);break;
            case 2: arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,Mucdos);break;


        }
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (i){
                        case 1: txtgioitinh.setText(gioitinhs[position]);break;
                        case 2: txtMucdo.setText(Mucdos[position]);break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.thongtin:
                Intent intent=new Intent(this,ThongTinCaloActivity.class);
                intent.putExtra("sdt",sdt);
                startActivity(intent);break;

        }
        return super.onOptionsItemSelected(item);
    }
}

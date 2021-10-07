package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinhvu.quanlysuckhoe.PreSenter.UserPreSenter;
import com.dinhvu.quanlysuckhoe.PreSenter.UserView;
import com.dinhvu.quanlysuckhoe.R;

public class DangKyActivity  extends AppCompatActivity
 implements UserView, View.OnClickListener {
    private EditText editSDT,editPass,editPass1;
    private Button btndangky;
    private UserPreSenter userPreSenter;
    private TextView txtdangnhap;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        btndangky.setOnClickListener(this::onClick);
        back.setOnClickListener(this);
    }

    private void Init() {
        userPreSenter=new UserPreSenter(this,this);
    }

    private void InitWidget() {
        back=findViewById(R.id.back);
        editSDT=findViewById(R.id.editsdt);
        editPass=findViewById(R.id.editpass);
        editPass1=findViewById(R.id.editpass1);
        btndangky=findViewById(R.id.btndangnhap);
    }

    @Override
    public void OnSucess() {
        Toast.makeText(this, "Thành Công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEmpty() {
        Toast.makeText(this, "Không để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError() {
        Toast.makeText(this, "Trùng Tên đăng nhập !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btndangnhap:
                String SDT=editSDT.getText().toString().trim();
                String Pass=editPass.getText().toString().trim();
                String Pass1=editPass1.getText().toString().trim();
                 if(Pass.equals(Pass1)){
                     userPreSenter.HandleDangKy(SDT,Pass);
                 }else{
                     Toast.makeText(this, "Mật khẩu không trùng !", Toast.LENGTH_SHORT).show();
                 }
                 break;
            case R.id.back:
                finish();break;
        }
    }
}

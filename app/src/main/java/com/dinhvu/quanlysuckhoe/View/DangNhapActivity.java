package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.PreSenter.UserPreSenter;
import com.dinhvu.quanlysuckhoe.PreSenter.UserView;
import com.dinhvu.quanlysuckhoe.R;

public class DangNhapActivity  extends AppCompatActivity
implements UserView, View.OnClickListener {
    private Button btndangnhap;
    private TextView txtdangky;
    private EditText editSDT,editPass;
    private UserPreSenter userPreSenter;
    private SharePrefeConfig sharePrefeConfig;
    private String sdt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        txtdangky.setOnClickListener(this::onClick);
        btndangnhap.setOnClickListener(this::onClick);
    }

    private void Init() {
        sharePrefeConfig=new SharePrefeConfig(this);
        sdt=sharePrefeConfig.getSDT();
        editSDT.setText(sdt);
        userPreSenter=new UserPreSenter(this,this);
    }

    private void InitWidget() {
        btndangnhap=findViewById(R.id.btndangnhap);
        txtdangky=findViewById(R.id.txtdangky);
        editSDT=findViewById(R.id.editsdt);
        editPass=findViewById(R.id.editpass);
    }

    @Override
    public void OnSucess() {
        Intent intent=new Intent(this,HomeActivity.class);
        sharePrefeConfig.PutSDT(editSDT.getText().toString().trim());
        startActivity(intent);


    }

    @Override
    public void OnEmpty() {
        Toast.makeText(this, "Không để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btndangnhap:
                String SDT=editSDT.getText().toString().trim();
                String Pass= editPass.getText().toString().trim();
                userPreSenter.getDataLogin(SDT,Pass);
                break;
            case R.id.txtdangky:
                startActivity(new Intent(this,DangKyActivity.class));break;

        }
    }
}

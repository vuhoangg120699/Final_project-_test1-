package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.User;
import com.dinhvu.quanlysuckhoe.PreSenter.UserPreSenter;
import com.dinhvu.quanlysuckhoe.PreSenter.UserView;
import com.dinhvu.quanlysuckhoe.R;

public class ChangePassActivity extends AppCompatActivity
 implements UserView {
    private Toolbar toolbar;
    private Button btncapnhat;
    private EditText editpass,editpass1;
    private User user;
    private UserPreSenter userPreSenter;
    private Intent intent;
    private String sdt;
    private TextView txtdangxuat;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanepass);
        InitWidget();
        Init();
    }

    private void Init() {
        SharePrefeConfig sharePrefeConfig=new SharePrefeConfig(this);
        sdt=sharePrefeConfig.getSDT();


        userPreSenter=new UserPreSenter(this,this);
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=editpass.getText().toString().trim();
                String pass1=editpass1.getText().toString().trim();
                if(pass.equals(pass1) && pass.length()>0){
                    userPreSenter.HandleUpdate(sdt,pass);
                }else{

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void InitWidget() {
        back=findViewById(R.id.back);
        btncapnhat=findViewById(R.id.btncapnhat);
        editpass=findViewById(R.id.editpass);
        editpass1=findViewById(R.id.editpass1);
    }

    @Override
    public void OnSucess() {
        Toast.makeText(this, "Thành Công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnEmpty() {
        Toast.makeText(this, "không để trống", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError() {
        Toast.makeText(this, "Lỗi", Toast.LENGTH_SHORT).show();
    }
}

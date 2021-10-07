package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.User;
import com.dinhvu.quanlysuckhoe.PreSenter.UserPreSenter;
import com.dinhvu.quanlysuckhoe.PreSenter.UserView;
import com.dinhvu.quanlysuckhoe.R;

public class FragMent_TaiKhoan extends AppCompatActivity
 implements UserView ,View.OnClickListener{

    private Button btncapnhat;
    private EditText editchieucao,editcannag,editten;
    private User user;
    private UserPreSenter userPreSenter;
    private Intent intent;
    private String sdt;
    private TextView txtdangxuat;
    private SharePrefeConfig sharePrefeConfig;
    private TextView txtchangepass;
    private ImageView back;
    private  TextView txtsdt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_taikhoan);
        InitWidget();
        Init();
    }

    private void Init() {

        sharePrefeConfig=new SharePrefeConfig(this);
        sharePrefeConfig.PutshareFer(this);
         editchieucao.setText(sharePrefeConfig.getHeight()+" cm");
         editcannag.setText(sharePrefeConfig.getWeight()+" kg");
         editten.setText(sharePrefeConfig.getName());
         txtsdt.setText(sharePrefeConfig.getSDT());



        userPreSenter=new UserPreSenter(this,this);
       btncapnhat.setOnClickListener(this);
        txtdangxuat.setOnClickListener(this);
        back.setOnClickListener(this);
        txtchangepass.setOnClickListener(this);
    }

    private void InitWidget() {
        txtsdt=findViewById(R.id.txtsdt);
       txtdangxuat=findViewById(R.id.txtdangxuat);
        btncapnhat=findViewById(R.id.btncapnhat);
       editcannag=findViewById(R.id.editcannang);
       editchieucao=findViewById(R.id.editchieucao);
       txtchangepass=findViewById(R.id.txtmatkhau);
       editten=findViewById(R.id.editname);
       back=findViewById(R.id.back);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncapnhat:
                String chieucao=editchieucao.getText().toString().trim().replace("cm","").trim();
                String cannang=editcannag.getText().toString().trim().replace("kg","").trim();
                String ten =editten.getText().toString().trim();
                if(chieucao.length()<0){
                    Toast.makeText(this, "Không để trống", Toast.LENGTH_SHORT).show();
                }
                if(cannang.length()<0){
                    Toast.makeText(this, "câng nặng không để trống", Toast.LENGTH_SHORT).show();
                }
                if(ten.length()<0){
                    Toast.makeText(this, "Tên không để trống", Toast.LENGTH_SHORT).show();
                }
                if(chieucao.length()>0 && cannang.length()>0 && ten.length()>0){
                    sharePrefeConfig.PutInfo(ten,Long.parseLong(chieucao),Long.parseLong(cannang));
                    editten.setText(ten);
                    editcannag.setText(cannang+" kg");
                    editchieucao.setText(chieucao+" cm");
                    Toast.makeText(this, "Đã Lưu", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtmatkhau: startActivity(new Intent(this,ChangePassActivity.class));break;
            case R.id.txtdangxuat:
                sharePrefeConfig.PutSDT("");
                startActivity(new Intent(this,DangNhapActivity.class));finish();break;
            case R.id.back:
                finish();break;
        }
    }
}

package com.dinhvu.quanlysuckhoe.View;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.R;

public class HomeActivity  extends AppCompatActivity
    implements View.OnClickListener
{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Intent intent;
    private String sdt;
    private Fragment fm;
    private CardView c1,c2;
    private SharePrefeConfig sharePrefeConfig;
    private ImageView user;
    private TextView txttenuser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
c1.setOnClickListener(this);
c2.setOnClickListener(this);
;
user.setOnClickListener(this);
txttenuser.setOnClickListener(this::onClick);
    }

    private void Init() {
        sharePrefeConfig=new SharePrefeConfig(this);
        sharePrefeConfig.PutshareFer(this);
        if(sharePrefeConfig.getName()!=null){
            txttenuser.setText(sharePrefeConfig.getName());
        }
        DiaLogInput();

    }

    private void InitWidget() {

          txttenuser=findViewById(R.id.txttenuser);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
     ;
        user=findViewById(R.id.user);
    }
    private void DiaLogInput(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_user_input);
        if(sharePrefeConfig.getName().length()>6){

        }else{
            dialog.show();
        }

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText editname=dialog.findViewById(R.id.editten);
        EditText editchieucao=dialog.findViewById(R.id.editchieucao);
        EditText editcannang=dialog.findViewById(R.id.editcannang);
        Button btnxacnhan=dialog.findViewById(R.id.btnxacnhan);
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name=editname.getText().toString().trim();
               String chieucao=editchieucao.getText().toString().trim();
               String cannang=editcannang.getText().toString().trim();
               if(name.length()<4){
                   Toast.makeText(HomeActivity.this, "Tên ít nhất  4  ký tự", Toast.LENGTH_SHORT).show();
               }
             if(name.length()>6 && chieucao.length()>0 &&cannang.length()>0){
                 txttenuser.setText(name);
                 sharePrefeConfig.PutInfo(name,Long.parseLong(chieucao),Long.parseLong(cannang));
                 dialog.cancel();
             }else{
                 Toast.makeText(HomeActivity.this,"Không để trống bất cứ thông tin nào",Toast.LENGTH_LONG).show();
             }
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1: startActivity(new Intent(this,FragMent_ManagerWatch.class));break;
            case R.id.c2: startActivity(new Intent(this,FragMent_NuocUong.class));break;
            case R.id.user: case R.id.txttenuser:
                startActivity(new Intent(this,FragMent_TaiKhoan.class));break;
        }
    }
}

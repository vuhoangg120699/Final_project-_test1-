package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.dinhvu.quanlysuckhoe.R;

public class FragMent_NuocUong extends AppCompatActivity
implements View.OnClickListener {
    private CardView c1,c2;

    private ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_manager);
        InitWidget();Init();
        HandleEvents();
    }

    private void Init() {

    }

    private void HandleEvents() {
        c1.setOnClickListener(this::onClick);
        c2.setOnClickListener(this::onClick);
        back.setOnClickListener(this);

    }

    private void InitWidget() {
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        back=findViewById(R.id.back);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1: startActivity(new Intent(this,NoteDrinkActivity.class));break;
            case R.id.c2: startActivity(new Intent(this,LichSuNuocUongActivity.class));break;
            case R.id.back:finish();break;

        }
    }
}

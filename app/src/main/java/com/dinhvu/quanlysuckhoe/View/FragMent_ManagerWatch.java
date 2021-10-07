package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.dinhvu.quanlysuckhoe.R;

public class FragMent_ManagerWatch extends AppCompatActivity
implements View.OnClickListener {
    private Toolbar toolbar;
    private CardView c1,c2,c3,c4;
    private Intent intent,intent1;
    private String sdt;
    private View view;
    private LottieAnimationView l1,l2,l3,l4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_manager);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        intent=getIntent();
        sdt=intent.getStringExtra("sdt");

        AssetManager assetManager=getAssets();
        l1.setImageAssetsFolder("calories.png");

        l2.setImageAssetsFolder("calories.png");
        l3.setImageAssetsFolder("calories.png");
        l4.setImageAssetsFolder("calories.png");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                l1.setAnimation(R.raw.bmi);
                l1.loop(true);
                l1.setSpeed(1f);
                l1.playAnimation();

                l2.setAnimation(R.raw.calo);
                l2.loop(true);
                l2.setSpeed(1f);
                l2.playAnimation();

                l2.playAnimation();

                l3.setAnimation(R.raw.history);
                l3.loop(true);
                l3.setSpeed(1f);
                l3.playAnimation();

                l4.setAnimation(R.raw.history);
                l4.loop(true);
                l4.setSpeed(1f);
                l4.playAnimation();


            }
        },2000);



    }

    private void HandleEvents() {
        c1.setOnClickListener(this::onClick);
        c2.setOnClickListener(this::onClick);
        c3.setOnClickListener(this::onClick);
        c4.setOnClickListener(this::onClick);
    }

    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1:
                intent1=new Intent(this,IndexBodyActivity.class);
               startActivity(intent1);break;
            case R.id.c2:
                intent1=new Intent(this,IndexCaloActivity.class);
                startActivity(intent1);break;
            case R.id.c3:
                intent1=new Intent(this,ThongTinBMIActivity.class);
                startActivity(intent1);break;
            case R.id.c4:
                intent1=new Intent(this,ThongTinCaloActivity.class);
                startActivity(intent1);break;
        }
    }
}

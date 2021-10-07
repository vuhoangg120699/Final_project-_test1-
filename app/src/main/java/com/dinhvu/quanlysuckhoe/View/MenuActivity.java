package com.dinhvu.quanlysuckhoe.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.dinhvu.quanlysuckhoe.R;

public class MenuActivity  extends AppCompatActivity {
    private Button btnmenu,btntienich;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnmenu=findViewById(R.id.btnmenu);
        btntienich=findViewById(R.id.btntienich);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,DangNhapActivity.class));
            }
        });
        btntienich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ManagerTienIchActivity.class));
            }
        });
    }
}

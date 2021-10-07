package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.Adapter.LichSuNuocUongAdapter;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LichSuNuocUongActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView listView;
    private ArrayList<NuocUong>arrayList;
    private LichSuNuocUongAdapter adapter;
    private NuocUong nuocUong;
    private CalendarView calendarView;
    private TextView txtphantram,txtml;
    private ProgressBar progressBar;
    private SharePrefeConfig sharePrefeConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_nuocuong);
        InitWidget();
        Init();
    }

    private void Init() {
sharePrefeConfig=new SharePrefeConfig(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lịch Sử");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nuocUong=new NuocUong(this);

        Calendar calendar=Calendar.getInstance();

        calendarView.setDate(calendar.getTimeInMillis());
      calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
          @Override
          public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
              int tong=0;
              int max=0;
              String thetich="";

              calendar.set(year,month,dayOfMonth);
              SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
              arrayList=nuocUong.getDataListNuocUongLichSu(simpleDateFormat.format(calendar.getTime()),sharePrefeConfig.getSDT());
                 Log.d("CHECKEDSIZE",arrayList.size()+" ");

              for(NuocUong nuocUong :arrayList){
                  tong+=nuocUong.getSudung();
                  thetich=nuocUong.getThetich();
                  Log.d("CHECKED",tong+" ");

              }

              if(thetich.length()>0){
                  progressBar.setMax(Integer.parseInt(thetich.replace("ml","").trim()));
                  txtml.setText(tong+" / "+thetich);
                  progressBar.setProgress(tong);
                  txtphantram.setText(String.format("%.1f",(float)tong*100/Integer.parseInt(thetich.replace("ml","").trim()))+"%");

              }else{
                  Log.d("CHECKED",simpleDateFormat.format(calendar.getTime()));
              }


          }
      });
    }

    private void InitWidget() {
        progressBar=findViewById(R.id.progress);
        toolbar=findViewById(R.id.toolbar);
        listView=findViewById(R.id.lv);
        calendarView=findViewById(R.id.calendarview);
        txtml=findViewById(R.id.txtml);
        txtphantram=findViewById(R.id.txtphantram);
    }
}

package com.dinhvu.quanlysuckhoe.View;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dinhvu.quanlysuckhoe.Adapter.NuocUongAdapter;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;
import com.dinhvu.quanlysuckhoe.Services.AlamServer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SettingNuocUongActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private SharePrefeConfig sharePrefeConfig;
    private Intent intent;
    private NuocUong nuocUong;
    private GridView gv;
    private Button btntaonuoc;
    private Intent intent1;
    private String sdt;
    private TextView txtmuctieu,txtmucsudung;
    private ArrayList<NuocUong> arrayList;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int Sudung=0;
    private SimpleDateFormat simpleDateFormat;
    private TextView txtsudung,txttong,txttime;
    private  int ml=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_drink);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {

    }

    private void Init() {


      setSupportActionBar(toolbar);
      getSupportActionBar().setTitle("Setting");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              startActivity(new Intent(SettingNuocUongActivity.this,NoteDrinkActivity.class));
              finish();
          }
      });
      Hienthi();
        Update();

    }




    private void InitWidget() {
        toolbar=findViewById(R.id.toolbar);
        gv=findViewById(R.id.gv);

    }
    private void Update() {

        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DateClockTimerNotification(arrayList.get(position).getGio(),arrayList.get(position).getMa(),
                        arrayList.get(position).getChuasudung());
                return true;
            }
        });
    }
    private void DateClockTimerNotification(String gio, int ma,int sudung) {

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_hengio_update);
        dialog.show();
        Button btnsudung,btnhengio;
        TextView txtgio;
        btnsudung=dialog.findViewById(R.id.btnsudung);
        btnhengio=dialog.findViewById(R.id.btnhengio);
        txtgio=dialog.findViewById(R.id.txttimer);
        Calendar calendar=Calendar.getInstance();
        Calendar calendar1=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);

        txtgio.setText(gio);
        txtgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(SettingNuocUongActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                               calendar1.set(0,0,0,hourOfDay,minute);
                               calendar.set(0,0,0,hourOfDay,minute);
                                SimpleDateFormat spf=new SimpleDateFormat("HH:mm");
                                txtgio.setText(spf.format(calendar.getTime()));

                            }
                        },hour,phut,true);
                timePickerDialog.show();
            }
        });
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);

        btnhengio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1=new Intent(SettingNuocUongActivity.this, AlamServer.class);
                pendingIntent= PendingIntent.getBroadcast(SettingNuocUongActivity.this,
                        0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
                nuocUong.HandleUpdateLuaChon(ma, 1);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(),pendingIntent);
                nuocUong.HandleUpdateGio(ma,txtgio.getText().toString().trim());
                dialog.cancel();
                Hienthi();

            }
        });
        btnsudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuocUong.HandleUpdateSuDung(ma,sudung,2);
                dialog.cancel();
                Hienthi();
                startActivity(new Intent(SettingNuocUongActivity.this,NoteDrinkActivity.class));
                finish();

            }
        });

    }


    private void Hienthi() {
        sharePrefeConfig=new SharePrefeConfig(this);
        sdt=sharePrefeConfig.getSDT();
        nuocUong=new NuocUong(this);
        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        arrayList=nuocUong.getDataListNuocUong(simpleDateFormat.format(calendar.getTime()),sdt);
        if(arrayList.size()>0){
            NuocUongAdapter adapter=new NuocUongAdapter(this,arrayList);
            gv.setAdapter(adapter);

        }
    }
}
